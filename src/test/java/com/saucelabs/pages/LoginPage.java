package com.saucelabs.pages;

import com.saucelabs.xpaths.LoginPaths;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    public LoginPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return emailAlert;
    }

    /* ELEMENTOS*/
    @FindBy(id = "tiet_sign_in_username")
    private WebElement emailInput;

    @FindBy(id = "tiet_sign_in_password")
    private WebElement passwordInput;

    @FindBy(id = " btn_sign_in_button")
    private WebElement iniciarSesionBtn;

    @FindBy(id = "tv_sign_in_click_contact")
    private WebElement contactarAdmin;

    @FindBy(id = "tv_sign_in_click_here")
    private WebElement recuperarContrasena;

    @FindBy(xpath = LoginPaths.ALERTA_EMAIL)
    private WebElement emailAlert;

    @FindBy(xpath = LoginPaths.ALERTA_PASSWORD)
    private WebElement passwordAlert;

    @FindBy(id = "message")
    private WebElement popupAlertMsg;

    @FindBy(id = "text_input_end_icon")
    private WebElement mostrarContrasenaBtn;

    @FindBy(id = "button1")
    private WebElement popupOkBtn;


    /* METOODOS */
    public void clicContactarAdmin() {
        contactarAdmin.click();
    }

    public void clicRecuperarContrasena() {
        recuperarContrasena.click();
    }

    public void clicPopupOkBtn() {
        popupOkBtn.click();
    }

    public void clicMostrarContrasenaBtn() {
        mostrarContrasenaBtn.click();
    }

    public void clicIniciarSesionBtn() {
        iniciarSesionBtn.click();
    }

    public void setTextEmailInput(String str) {
        emailInput.sendKeys(str);
    }

    public void setTextPasswordInput(String str) {
        passwordInput.sendKeys(str);
    }

    public boolean popupAlertMsgContains(String msg) {
        return popupAlertMsg.getText().contains(msg);
    }
}

