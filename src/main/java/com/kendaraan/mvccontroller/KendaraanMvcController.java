package com.kendaraan.mvccontroller;

import com.kendaraan.dto.MstKendaraanDto;
import com.kendaraan.model.MstKendaraan;
import com.kendaraan.repository.KendaraanRepository;
import com.kendaraan.service.KendaraanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @ResponseBody
    public ResponseEntity<String> saveKendaraan(@RequestBody MstKendaraanDto mstKendaraanDto) {
        ResponseEntity<?> response = kendaraanService.createKendaraan(mstKendaraanDto);
        return (ResponseEntity<String>) response;
    }

    @GetMapping("/edit/{kendaraanId}")
    public String tampilkanFormEditKendaraan(@PathVariable Long kendaraanId, Model model) {
        Optional<MstKendaraan> kendaraanOpt = kendaraanService.getKendaraanById(kendaraanId);
        if (kendaraanOpt.isPresent()) {
            MstKendaraanDto mstKendaraanDto = new MstKendaraanDto(kendaraanOpt.get());
            model.addAttribute("mstKendaraanDto", mstKendaraanDto);
            return "kendaraan/edit-kendaraan";
        } else {
            model.addAttribute("error", "Kendaraan not found");
            return "kendaraan/form-kendaraan";
        }
    }

    @PutMapping(value = "/edit/{kendaraanId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> editKendaraan(@PathVariable Long kendaraanId, @RequestBody MstKendaraanDto mstKendaraanDto) {
        ResponseEntity<?> response = kendaraanService.updateKendaraan(kendaraanId, mstKendaraanDto);
        return (ResponseEntity<String>) response;
    }

    @GetMapping("/delete/{kendaraanId}")
    public String deleteKendaraan(@PathVariable Long kendaraanId, Model model) {
        kendaraanService.deleteKendaraan(kendaraanId);
        return "redirect:/kendaraan/list";
    }
}
