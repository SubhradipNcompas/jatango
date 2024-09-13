/**
 * Created By Subhradip Sinha
 * Date: 9/11/2024
 * Project Name: jatango
 */

package com.Jatango.demo.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/resources/features/login.feature",
        glue = {"demo/stepdefs"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
        tags="@Jatango_Login",
        monochrome = true)
public class login {
}
