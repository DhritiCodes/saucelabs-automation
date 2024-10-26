package com.swaglabsdemo.pages;

import com.swaglabsdemo.utils.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    private final static Logger logger = LogUtil.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "inventory_item")
    private List<WebElement> products;

    @FindBy(css="img.inventory_item_img")
    private List<WebElement> productImages;

    @FindBy(id="add-to-cart-sauce-labs-backpack")
    private List<WebElement> productAddToCartButtons;

    @FindBy(className="shopping_cart_badge")
    private WebElement cartBadge;

    public List<WebElement> getProducts() {
        return products;
    }

    public boolean areProductsDisplayed() {
        int count = products.size();
        logger.debug("Checking if products are displayed. Total products found: " + count);
        return count > 0;
    }

    public List<String> getProductTitles() {
        List<String> titles = products.stream()
                .map(prod -> prod.findElement(By.className("inventory_item_name")).getText())
                .collect(Collectors.toList());
        logger.debug("Product titles retrieved: " + titles);
        return titles;
    }

    public List<String> getProductDescriptions() {
        List<String> descriptions = products.stream()
                .map(prod -> prod.findElement(By.className("inventory_item_desc")).getText())
                .collect(Collectors.toList());
        logger.debug("Product descriptions retrieved: " + descriptions);
        return descriptions;
    }

    public List<Double> getProductPrices() {
        List<Double> prices = products.stream()
                .map(prod -> prod.findElement(By.className("inventory_item_price")).getText())
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .collect(Collectors.toList());
        logger.debug("Product prices retrieved: " + prices);
        return prices;
    }

    public List<String> getInvalidProductImageUrls() {
        List<String> invalidImageUrls = productImages.stream()
                .map(img -> img.getAttribute("src"))
                .filter(src -> !isValidImage(src))
                .collect(Collectors.toList());
        if (invalidImageUrls.isEmpty()) {
            logger.debug("All product images are valid.");
        } else {
            logger.warn("Invalid product images found with URLs: " + invalidImageUrls);
        }
        return invalidImageUrls;
    }

    public boolean isValidImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            logger.debug("Image URL: " + imageUrl + " returned status code: " + responseCode);
            return responseCode == 200;
        } catch (Exception e) {
            logger.error("Could not validate image URL: " + imageUrl + ". Error: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean areAllAddToCartButtonsDisplayed() {
        boolean allDisplayed = productAddToCartButtons.stream()
                .allMatch(btn -> btn.isDisplayed() && btn.isEnabled());
        if (allDisplayed) {
            logger.debug("All 'Add to Cart' buttons are displayed and enabled.");
        } else {
            logger.warn("Some 'Add to Cart' buttons are either not displayed or disabled.");
        }
        return allDisplayed;
    }

    public void addProductToCart(String productName) {

        int flag = 1;

        for (WebElement product : products) {
            String title = product.findElement(By.className("inventory_item_name")).getText();
            if (title.equals(productName)) {
                WebElement addToCartBtn = product.findElement(By.className("btn_inventory"));
                addToCartBtn.click();
                logger.info("Clicked 'Add to cart' button for product: " + productName);
                flag = 0;
            }
//            }else{
//                logger.warn("Product with name '" + productName + "' not found in the product list.");
//            }
        }

        if(flag == 1 )
        {
                logger.warn("Product with name '" + productName + "' not found in the product list.");
        }
    }

    public boolean isRemoveBtnVisible(String productName) {
        boolean removeBtnVisible = products.stream()
                .filter(prod -> prod.findElement(By.className("inventory_item_name")).getText().equals(productName))
                .map(prod -> prod.findElement(By.className("btn_inventory")))
                .anyMatch(prod -> prod.getText().equals("Remove") && prod.isDisplayed());
        logger.debug("Remove button visibility for product '" + productName + "': " + removeBtnVisible);
        return removeBtnVisible;
    }

    public int getCartQuantity() {
        int quantity = Integer.parseInt(cartBadge.getText());
        logger.debug("Cart quantity displayed: " + quantity);
        return quantity;
    }


}




