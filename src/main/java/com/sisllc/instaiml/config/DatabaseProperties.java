/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisllc.instaiml.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "spring.r2dbc")
@Component
public class DatabaseProperties {
   
    private String h2Url;
    private String h2Username;
    private String h2Password;
    private String h2DdlSchemaDir;
    private String h2Host;
    private String h2Port;
    private String h2Database;
    
    private String mockUrl;
    private String mockUsername;
    private String mockPassword;
    private String mockDdlSchemaDir;
    private String mockHost;
    private String mockPort;
    private String mockDatabase;
    
    private String pgUrl;
    private String pgUsername;
    private String pgPassword;
    private String pgDdlSchemaDir;
    private String pgHost;
    private String pgPort;
    private String pgDatabase;
    
    private String prodUrl;
    private String prodUsername;
    private String prodPassword;
    private String prodDdlSchemaDir;
    private String prodHost;
    private String prodPort;
    private String prodDatabase;
    
    private String h2TcpServerPort;
    private String h2WebServerPort;

    private ProfileSetting profileSetting;
    private String ddlSchemaDir;
    private Boolean setupMockUserOnly;
    private Boolean truncateMockData;
    private String databaseUsed;
    private Boolean skipDataInit;
    
    public static enum ProfileSetting {
        H2, MOCK, PG, PROD
    }
    
    public void setupBaseDbProps(ProfileSetting ps) {
        profileSetting = ps;
        
        switch(profileSetting) {
            case ProfileSetting.PROD -> {
                ddlSchemaDir = prodDdlSchemaDir;
                databaseUsed = this.prodDatabase;
            }
            case ProfileSetting.H2 -> {
                ddlSchemaDir = h2DdlSchemaDir;
                databaseUsed = this.h2Database;
            }
            case ProfileSetting.MOCK -> {
                ddlSchemaDir = mockDdlSchemaDir;
                databaseUsed = this.mockDatabase;               
            }
            default -> {
                ddlSchemaDir = pgDdlSchemaDir;
                databaseUsed = this.pgDatabase;                
            }
        }
    }
}
