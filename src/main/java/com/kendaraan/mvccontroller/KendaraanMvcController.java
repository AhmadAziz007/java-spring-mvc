package com.kendaraan.mvccontroller;

import com.kendaraan.dto.MstKendaraanDto;
import com.kendaraan.model.MstKendaraan;
import com.kendaraan.service.KendaraanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/kendaraan")
public class KendaraanMvcController {

    @Autowired
    private KendaraanService kendaraanService;

    @GetMapping("/list")
    public String tampilanDaftarKendaraan(
            Model model,
            @RequestParam(required = false) String noRegistrasi,
            @RequestParam(required = false) String namaPemilik) {

        List<MstKendaraan> kendaraanList = kendaraanService.getKendaraan(noRegistrasi, namaPemilik);
        model.addAttribute("kendaraanList", kendaraanList);
        return "kendaraan/form-kendaraan";
    }

    @GetMapping("/add")
    public String tampilkanFormTambahKendaraan(Model model) {
        MstKendaraanDto mstKendaraanDto = new MstKendaraanDto();
        model.addAttribute("mstKendaraanDto", mstKendaraanDto);
        return "kendaraan/add-kendaraan";
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveKendaraan(@RequestBody MstKendaraanDto mstKendaraanDto) {
        kendaraanService.createKendaraan(mstKendaraanDto);
        return "redirect:/kendaraan/list";
    }
}
