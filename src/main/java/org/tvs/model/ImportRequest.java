package org.tvs.model;

import org.springframework.web.multipart.MultipartFile;

public class ImportRequest {
  private MultipartFile file;

  public ImportRequest(MultipartFile file) {
    this.file = file;
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }
}
