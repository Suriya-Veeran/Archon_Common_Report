package com.p3solutions.archon_report_utility.core;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstactReport {
  public static final String PDF = ".pdf";
  protected PageSize pageSize = PageSize.A4;
  protected Document document;

  void setPageSize(PageSize pageSize) {
    this.pageSize = pageSize;
  }

  AbstactReport() throws IOException {}

  AbstactReport(String outputPath) throws IOException {
    initialize(outputPath);
  }

  AbstactReport(String outputPath, String filename) throws IOException {
    initialize(outputPath, filename);
  }

  private void initialize(String outputPath, String filename) throws IOException {
    File outputFile = checkOutputPathExists(outputPath);
    if (!filename.endsWith(PDF)) {
      throw new FileNotFoundException("Invalid pdf file name : " + filename);
    }
    File outputPdfFile =
        createOutputFile(outputFile.getAbsolutePath() + File.separator + filename, outputFile);
    document =
        new Document(
            new PdfDocument(new PdfWriter(outputPdfFile.getAbsolutePath())), pageSize, false);
  }

  private static File createOutputFile(String outputFile, File outputFile1) throws IOException {
    File outputPdfFile = new File(outputFile);
    boolean newFile = outputPdfFile.createNewFile();
    if (!newFile) {
      throw new FileNotFoundException(
          "Unable to create a output file in a output path - " + outputFile1.getAbsolutePath());
    }
    return outputPdfFile;
  }

  private void initialize(String outputPath) throws IOException {
    File outputFile = checkOutputPathExists(outputPath);
    File outputPdfFile =
        createOutputFile(
            outputFile.getAbsolutePath() + File.separator + UUID.randomUUID().toString() + PDF,
            outputFile);
    document =
        new Document(
            new PdfDocument(new PdfWriter(outputPdfFile.getAbsolutePath())), pageSize, false);
  }

  private static File checkOutputPathExists(String outputPath) throws FileNotFoundException {
    File outputFile = new File(outputPath);
    if (!outputFile.exists()) {
      throw new FileNotFoundException("Output Path is not exists in the system");
    }
    return outputFile;
  }

  protected void close() {
    if (Objects.nonNull(document)) {
      document.flush();
      document.close();
    }
  }
}
