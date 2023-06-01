package ues.dsi.sistemaadopcionbackend.cloudinary;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadImpl implements FileUpload {
    private final Cloudinary cloudinary;


    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty");
        }

        try {
            Map uploadResult = cloudinary.uploader()
                    .upload(multipartFile.getBytes(),
                            Map.of("public_id", UUID.randomUUID().toString()));
            if (uploadResult != null && uploadResult.containsKey("url")) {
                return uploadResult.get("url").toString();
            }
        } catch (Exception e) {
            throw new IOException("Error uploading file to Cloudinary", e);
        }
        throw new IOException("Unable to upload file to Cloudinary");
    }
}