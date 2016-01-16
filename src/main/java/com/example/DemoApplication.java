package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.concurrent.Future;

@SpringBootApplication
@EnableAsync
@Controller
public class DemoApplication {
    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("request-download")
    @ResponseBody
    public DownloadStatus requestDownload(HttpSession session) {
        // dispatch worker
        Future<String> future = asyncService.async();
        DownloadStatus downloadStatus = DownloadStatus.create(future);
        session.setAttribute("downloadStatus", downloadStatus);
        return downloadStatus;
    }

	@RequestMapping("request-download/{id}")
    @ResponseBody
	public DownloadStatus requestDownload(@PathVariable String id, HttpSession session) {
        DownloadStatus downloadStatus = (DownloadStatus) session.getAttribute("downloadStatus");
        if (downloadStatus == null) {
            throw new NullPointerException("download status is null.");
        }
        return downloadStatus.isDone() ? downloadStatus.done() : downloadStatus;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
