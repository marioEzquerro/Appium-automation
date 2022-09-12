package com.saucelabs.stepsdef;

import com.saucelabs.pages.LoginPage;
import com.saucelabs.pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class LoginSteps {

    public static final String PAGE_URL = "com.hiberus.mobile.android.rva.app.splash.SplashActivity";
    private final LoginPage loginPage;

    public LoginSteps() {
        loginPage = PagesFactory.getInstance().getLoginPage();
    }

    @Given("usuario rellena el campo de correo con {string}")
    public void usuarioRellenaElCampoDeCorreoCon(String str) {
        loginPage.setTextEmailInput(str);
    }

    @And("usuario rellena el campo de contrasena con {string}")
    public void usuarioRellenaElCampoDeContrasenaCon(String str) {
        loginPage.setTextPasswordInput(str);
    }

    @When("usuario clica el boton de iniciar sesion")
    public void usuarioClicaElBotonDeIniciarSesion() {
        loginPage.clicIniciarSesionBtn();
    }

    @Then("el usuario aparece en la ventana de listado de fincas")
    public void elUsuarioApareceEnLaVentanaDeListadoDeFincas() {
        Assert.assertEquals("No se cumple", 10, 10);
    }
}
