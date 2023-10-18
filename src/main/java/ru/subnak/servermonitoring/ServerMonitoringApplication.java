package ru.subnak.servermonitoring;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.subnak.servermonitoring.entity.SystemInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class ServerMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerMonitoringApplication.class, args);
	}

}
