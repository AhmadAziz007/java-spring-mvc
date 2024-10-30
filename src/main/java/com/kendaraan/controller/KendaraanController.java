package com.kendaraan.controller;

import com.kendaraan.dto.MstKendaraanDto;
import com.kendaraan.model.MstKendaraan;
import com.kendaraan.service.KendaraanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/kendaraan")
public class KendaraanController {
    @Autowired
    private KendaraanService kendaraanService;

    @PostMapping("/save")
    public ResponseEntity<?> createKendaraan (@RequestBody MstKendaraanDto kendaraanDto){
        return kendaraanService.createKendaraan(kendaraanDto);
    }

    @PutMapping("/update/{kendaraanId}")
    public ResponseEntity<?> updateKendaraan (@PathVariable Long kendaraanId, @RequestBody MstKendaraanDto kendaraanDto){
        return kendaraanService.updateKendaraan(kendaraanId, kendaraanDto);
    }

    @PostMapping("/list-kendaraan")
    public List<MstKendaraan> getKendaraan(@RequestBody(required = false) MstKendaraanDto searchCriteria) {
        String noRegistrasi = searchCriteria != null ? searchCriteria.getNoRegistrasi() : null;
        String namaPemilik = searchCriteria != null ? searchCriteria.getNamaPemilik() : null;

        return kendaraanService.getKendaraan(noRegistrasi, namaPemilik);
    }

    @DeleteMapping("/delete/{kendaraanId}")
    public ResponseEntity<?> deleteKendaraan(@PathVariable Long kendaraanId){
        return kendaraanService.deleteKendaraan(kendaraanId);
    }
}
