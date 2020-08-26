package com.suharsono.arief.mitraiscodingtest.test;

import com.suharsono.arief.mitraiscodingtest.controller.RegistrationController;
import com.suharsono.arief.mitraiscodingtest.model.Registration;
import com.suharsono.arief.mitraiscodingtest.repository.RegistrationRepository;
import com.suharsono.arief.mitraiscodingtest.response.ResponseMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrationControllerTest {
    
    @Autowired
    private RegistrationController controller;
    
    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private MockMvc mvc;
    
    private static String testEmail;
    private static String testPhone;
    
    public static String testTokenForRequest;
    
    @BeforeClass
    public static void setUp() {
        Date now = new Date();
        long timestamp = now.getTime() / 1000;
        testEmail = "registration-test-" + timestamp + "@gmail.com";
        testPhone = "08" + timestamp;
    }
    
    @Test
    public void initialTest() throws Exception {
        assertThat(controller).isNotNull();
    }
    
    @Test
    public void test001_EmptyMobileNumber() throws Exception {
        // Should return 400 with warning message
        JSONObject request = new JSONObject();
        request.put("firstName", "arief");
        request.put("lastName", "suharsono");
        request.put("gender", "M");
        request.put("email", testEmail);
        request.put("birthDate", "08-04-1991");
        mvc.perform(post("/register")
                    .content(request.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(true)));
        
        // Check db, new data should not be inserted
        Set<Registration> listRegistration = registrationRepository.findByEmail(testEmail);
        Assert.assertEquals(0, listRegistration.size());
    }
    
    @Test
    public void test002_EmptyEmail() throws Exception {
        // Should return 400 with warning message
        JSONObject request = new JSONObject();
        request.put("firstName", "arief");
        request.put("lastName", "suharsono");
        request.put("gender", "M");
        request.put("mobileNumber", testPhone);
        request.put("birthDate", "08-04-1991");
        mvc.perform(post("/register")
                    .content(request.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(true)));
        
        // Check db, new data should not be inserted
        Set<Registration> listRegistration = registrationRepository.findByMobileNumber(testPhone);
        Assert.assertEquals(0, listRegistration.size());
    }
    
    @Test
    public void test003_EmptyFirstName() throws Exception {
        // Should return 400 with warning message
        JSONObject request = new JSONObject();
        request.put("lastName", "suharsono");
        request.put("gender", "M");
        request.put("mobileNumber", testPhone);
        request.put("email", testEmail);
        request.put("birthDate", "08-04-1991");
        mvc.perform(post("/register")
                    .content(request.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(true)));
        
        // Check db, new data should not be inserted
        Set<Registration> listRegistration = registrationRepository.findByMobileNumber(testPhone);
        Assert.assertEquals(0, listRegistration.size());
    }
    
    @Test
    public void test004_EmptyLastName() throws Exception {
        // Should return 400 with warning message
        JSONObject request = new JSONObject();
        request.put("firstName", "arief");
        request.put("gender", "M");
        request.put("mobileNumber", testPhone);
        request.put("email", testEmail);
        request.put("birthDate", "08-04-1991");
        mvc.perform(post("/register")
                    .content(request.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(true)));
        
        // Check db, new data should not be inserted
        Set<Registration> listRegistration = registrationRepository.findByMobileNumber(testPhone);
        Assert.assertEquals(0, listRegistration.size());
    }
    
    @Test
    public void test005_SuccessWithAllDataPresent() throws Exception {
        // Should return 200 with success message
        // Should return this message
        ResponseMessage rm = ResponseMessage.RegistrationSuccess;
        
        JSONObject request = new JSONObject();
        request.put("firstName", "arief");
        request.put("lastName", "suharsono");
        request.put("gender", "M");
        request.put("mobileNumber", testPhone + "1");
        request.put("email", testEmail + "a");
        request.put("birthDate", "08-04-1991");
        mvc.perform(post("/register")
                    .content(request.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(false)))
                .andExpect(jsonPath("$.code", is(rm.code)))
                .andExpect(jsonPath("$.message", is(rm.message)));
        
        // Check db, new data should be inserted
        Set<Registration> listRegistration = registrationRepository.findByMobileNumber(testPhone + "1");
        Assert.assertEquals(1, listRegistration.size());
    }
    
    @Test
    public void test006_SuccessWithNoBirthDatePresent() throws Exception {
        // Should return 200 with success message
        // Should return this message
        ResponseMessage rm = ResponseMessage.RegistrationSuccess;
        
        JSONObject request = new JSONObject();
        request.put("firstName", "arief");
        request.put("lastName", "suharsono");
        request.put("gender", "M");
        request.put("mobileNumber", testPhone + "2");
        request.put("email", testEmail + "b");
        mvc.perform(post("/register")
                    .content(request.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(false)))
                .andExpect(jsonPath("$.code", is(rm.code)))
                .andExpect(jsonPath("$.message", is(rm.message)));
        
        // Check db, new data should be inserted
        Set<Registration> listRegistration = registrationRepository.findByMobileNumber(testPhone + "2");
        Assert.assertEquals(1, listRegistration.size());
    }
    
    @Test
    public void test007_SuccessWithNoGenderPresent() throws Exception {
        // Should return 200 with success message
        // Should return this message
        ResponseMessage rm = ResponseMessage.RegistrationSuccess;
        
        JSONObject request = new JSONObject();
        request.put("firstName", "arief");
        request.put("lastName", "suharsono");
        request.put("mobileNumber", testPhone + "3");
        request.put("email", testEmail + "c");
        request.put("birthDate", "08-04-1991");
        mvc.perform(post("/register")
                    .content(request.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(false)))
                .andExpect(jsonPath("$.code", is(rm.code)))
                .andExpect(jsonPath("$.message", is(rm.message)));
        
        // Check db, new data should be inserted
        Set<Registration> listRegistration = registrationRepository.findByMobileNumber(testPhone + "3");
        Assert.assertEquals(1, listRegistration.size());
    }
    
    @Test
    public void test008_EmailExists() throws Exception {
        // Should return 200 with error message
        // Should return this message
        ResponseMessage rm = ResponseMessage.RegistrationEmailAlreadyExist;
        
        JSONObject request = new JSONObject();
        request.put("firstName", "arief");
        request.put("lastName", "suharsono");
        request.put("gender", "M");
        request.put("mobileNumber", testPhone + "4");
        request.put("email", testEmail + "a");
        request.put("birthDate", "08-04-1991");
        mvc.perform(post("/register")
                    .content(request.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(true)))
                .andExpect(jsonPath("$.code", is(rm.code)))
                .andExpect(jsonPath("$.message", is(rm.message)));
        
        // Check db, new data should not be inserted
        Set<Registration> listRegistration = registrationRepository.findByMobileNumber(testPhone + "4");
        Assert.assertEquals(0, listRegistration.size());
    }
    
    @Test
    public void test009_MobileNumberExists() throws Exception {
        // Should return 200 with error message
        // Should return this message
        ResponseMessage rm = ResponseMessage.RegistrationMobileNumberAlreadyExist;
        
        JSONObject request = new JSONObject();
        request.put("firstName", "arief");
        request.put("lastName", "suharsono");
        request.put("gender", "M");
        request.put("mobileNumber", testPhone + "1");
        request.put("email", testEmail + "z");
        request.put("birthDate", "08-04-1991");
        mvc.perform(post("/register")
                    .content(request.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(true)))
                .andExpect(jsonPath("$.code", is(rm.code)))
                .andExpect(jsonPath("$.message", is(rm.message)));
        
        // Check db, new data should not be inserted
        Set<Registration> listRegistration = registrationRepository.findByEmail(testEmail + "z");
        Assert.assertEquals(0, listRegistration.size());
    }
    
    @Test
    public void test010_MobileNumberInvalid() throws Exception {
        // Should return 400 with error message
        // Should return this message
        ResponseMessage rm = ResponseMessage.RegistrationMobileNumberInvalid;
        
        JSONObject request = new JSONObject();
        request.put("firstName", "arief");
        request.put("lastName", "suharsono");
        request.put("gender", "M");
        request.put("mobileNumber", testPhone + "1111111111111");
        request.put("email", testEmail + "z");
        request.put("birthDate", "08-04-1991");
        mvc.perform(post("/register")
                    .content(request.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(true)))
                .andExpect(jsonPath("$.code", is(rm.code)))
                .andExpect(jsonPath("$.message", is(rm.message)));
        
        // Check db, new data should not be inserted
        Set<Registration> listRegistration = registrationRepository.findByEmail(testEmail + "z");
        Assert.assertEquals(0, listRegistration.size());
    }
    
    @Test
    public void test011_WrongBirthDateFormat() throws Exception {
        // Should return 400 with error message
        // Should return this message
        ResponseMessage rm = ResponseMessage.RegistrationBirthdateInvalid;
        
        JSONObject request = new JSONObject();
        request.put("firstName", "arief");
        request.put("lastName", "suharsono");
        request.put("gender", "M");
        request.put("mobileNumber", testPhone + "9");
        request.put("email", testEmail + "z");
        request.put("birthDate", "asdfasdf");
        mvc.perform(post("/register")
                    .content(request.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is(true)))
                .andExpect(jsonPath("$.code", is(rm.code)))
                .andExpect(jsonPath("$.message", is(rm.message)));
        
        // Check db, new data should not be inserted
        Set<Registration> listRegistration = registrationRepository.findByEmail(testEmail + "z");
        Assert.assertEquals(0, listRegistration.size());
    }
    
}
