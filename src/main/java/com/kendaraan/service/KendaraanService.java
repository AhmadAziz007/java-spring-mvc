package com.kendaraan.service;

import com.kendaraan.dto.MstKendaraanDto;
import com.kendaraan.model.MstKendaraan;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KendaraanService {
    public ResponseEntity<?> createKendaraan(MstKendaraanDto mstKendaraanDto);
    public ResponseEntity<?> updateKendaraan(Long kendaraanId, MstKendaraanDto mstKendaraanDto);
    public  List<MstKendaraan> getKendaraan(String noRegistrasi, String namaPemilik);
    public ResponseEntity<?> deleteKendaraan(Long kendaraanId);
}
