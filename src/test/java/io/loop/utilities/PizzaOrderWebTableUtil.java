package io.loop.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PizzaOrderWebTableUtil {

    /**
     * @param driver
     * @param name
     * @param field
     */
    public static String returnFieldValue(WebDriver driver, String name, String field){
        String index = "";

        if(field.equalsIgnoreCase("Pizza Type")){
            index = "[1]";
        } else if (field.equalsIgnoreCase("Amount")) {
            index = "[2]";
        } else if (field.equalsIgnoreCase("Date")) {
            index = "[3]";
        } else if (field.equalsIgnoreCase("Street")) {
            index = "[4]";
        } else if (field.equalsIgnoreCase("City")) {
            index = "[5]";
        } else if (field.equalsIgnoreCase("State")) {
            index = "[6]";
        } else if (field.equalsIgnoreCase("Zip")) {
            index = "[7]";
        } else if (field.equalsIgnoreCase("Card")) {
            index = "[8]";
        } else if (field.equalsIgnoreCase("Card Number")) {
            index = "[9]";
        } else if (field.equalsIgnoreCase("Exp")) {
            index = "[10]";
        } else {
            System.out.println("There is not such a field: " + field);
        }
        WebElement element = driver.findElement(By.xpath("//td[.='"+name+"']//following-sibling::td"+index));
        return element.getText().trim();

    }
    /*
    public static String returnAnyFieldNewSwitch(WebDriver driver, String emailAddress, String field) throws InterruptedException {
        String xpath = switch (field.toLowerCase()) {
            case "full name" -> "//td[2][text()='" + emailAddress + "']/preceding-sibling::td//span[2]";
            case "username" -> "//td[2][text()='" + emailAddress + "']/following-sibling::td[1]";
            case "inviter" -> "//td[2][text()='" + emailAddress + "']/following-sibling::td[2]";
            case "phone number" -> "//td[2][text()='" + emailAddress + "']/following-sibling::td[3]/span";
            case "role" -> "//td[2][text()='" + emailAddress + "']/following-sibling::td[4]/span/span";
            case "advisor" -> "//td[2][text()='" + emailAddress + "']/following-sibling::td[5]";
            default -> throw new InterruptedException("No such field exists: " + field);
        };

        WebElement element = driver.findElement(By.xpath(xpath));
        return element.getText().trim();
    }
     */

}