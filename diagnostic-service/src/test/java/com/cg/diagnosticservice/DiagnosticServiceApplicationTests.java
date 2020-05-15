package com.cg.diagnosticservice;

import com.cg.diagnosticservice.dto.DiagnosticCenterDto;
import com.cg.diagnosticservice.entity.DiagnosticCenter;
import com.cg.diagnosticservice.services.DiagnosticCenterService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DiagnosticServiceApplicationTests {
    @Mock
    DiagnosticCenterService diagnosticCenterService;
    final String token="eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1OTAzODM4MTQsInN1YiI6InZhbnNodGFsd2FyMEBnbWFpbC5jb20iLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiQURNSU4ifV0sImlzcyI6ImhlYWx0aGNhcmUifQ.Pln1Vcn23Bad2KYfWk0xg4nvqyItD-b_rTYkNXuQwzu1QzbcFejORrhb36DTy6DFT2LYCAdeBAzfyp5ITxVktw";

    @Test
    void addDiagnosticCenter() {
        DiagnosticCenter diagnosticCenter=new DiagnosticCenter();
        Mockito.when(diagnosticCenterService.addCenter(token,new DiagnosticCenterDto("hello","hello","1234567890"))).thenReturn(diagnosticCenter);
    }

}
