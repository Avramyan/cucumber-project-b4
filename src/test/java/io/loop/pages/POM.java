package io.loop.pages;

public class POM {

    private LoginPage loginPage;
    private GoogleSearchPage googleSearchPage;
    private ProductPage productPage;


    public LoginPage getLoginPage() {
        if(loginPage==null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public GoogleSearchPage getGoogleSearchPage() {
        if(googleSearchPage == null){
            googleSearchPage = new GoogleSearchPage();
        }
        return googleSearchPage;
    }

    public ProductPage getProductPage() {
        if(productPage == null){
            productPage = new ProductPage();
        }
        return productPage;
    }
}