package com.banking.bankingapp.config;

import java.util.Arrays;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;



public class BankingAppConfiguration {

	public OpenAPI swaggerDocOpenApi()
	{
		Server developmentserver=new Server(); 
		developmentserver.setUrl("http://localhost:8080");
		developmentserver.setDescription("this is for development");
		
		Server productionserver=new Server(); 
		productionserver.setUrl("http://localhost:8080");
		productionserver.setDescription("this is for production");
		
		Contact contact=new Contact();
		contact.setName("Banking application");
		contact.setEmail("help.bank.in");
		contact.setUrl("https://mvnrepository.com/");
		
		License license=new License();
		license.setName("2 year license");
		license.setUrl("write license proviser's url");
		
		Info info=new Info();
		info.title("Global Bank");
		info.version("1.0");
		info.contact(contact);
		info.description("designed for banking");
		info.termsOfService("pass url");
		info.license(license);
		
		OpenAPI openapi=new OpenAPI();
		openapi.info(info);
		openapi.servers(Arrays.asList(developmentserver,productionserver));
		
		return openapi;
		
		
		
	}
}
