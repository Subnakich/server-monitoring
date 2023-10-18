package ru.subnak.servermonitoring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.subnak.servermonitoring.entity.SystemInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Controller
@RequestMapping("/")
public class MonitoringController {
    static boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

    @GetMapping
    public String main(Model model) {
        SystemInfo systemInfo = getResource();

        model.addAttribute("CPUUsage", systemInfo.getCPUUsage());
        model.addAttribute("RAMFree", ((float) systemInfo.getMemory().getFree() / (1024 * 1024)));
        model.addAttribute("RAMUsed", ((float) systemInfo.getMemory().getUsed() / (1024 * 1024)));

        return "monitoring";
    }

    @GetMapping("/get-data")
    @ResponseBody
    public SystemInfo getData() {
        return getResource();
    }

    public SystemInfo getResource() {

        try {

            ProcessBuilder processBuilder;
            if (isWindows) {
                // Запуск скрипта PowerShell
                processBuilder = new ProcessBuilder("powershell.exe", "-ExecutionPolicy", "Bypass", "src/main/java/ru/subnak/servermonitoring/script/win.ps1");
            } else {
                // Запуск скрипта Linux
                processBuilder = new ProcessBuilder("/bin/bash", "src/main/java/ru/subnak/servermonitoring/script/lin.sh");
            }

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Чтение вывода скрипта
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            String outputResult = output.toString().trim().replaceAll("\\s+", " ");

            // Ожидание завершения процесса
            int exitCode = process.waitFor();
            System.out.println("Script executed with exit code: " + exitCode);

            SystemInfo systemInfo = SystemInfo.fromJson(outputResult);
            System.out.println(systemInfo);
            return systemInfo;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new SystemInfo();
    }
}
