package org.jabref.logic.externalfiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.jabref.logic.importer.OpenDatabase;
import org.jabref.logic.importer.ParserResult;
import org.jabref.logic.importer.fileformat.PdfContentImporter;
import org.jabref.logic.importer.fileformat.PdfXmpImporter;
import org.jabref.logic.preferences.TimestampPreferences;
import org.jabref.model.util.FileUpdateMonitor;
import org.jabref.preferences.PreferencesService;

public class ExternalFilesContentImporter {

    private final PreferencesService preferencesService;
    private final TimestampPreferences timestampPreferences;

    public ExternalFilesContentImporter(PreferencesService preferencesService, TimestampPreferences timestampPreferences) {
        this.preferencesService = preferencesService;
        this.timestampPreferences = timestampPreferences;
    }

    public ParserResult importPDFContent(Path file) {
        return new PdfContentImporter(preferencesService).importDatabase(file, StandardCharsets.UTF_8);
    }

    public ParserResult importXMPContent(Path file) {
        return new PdfXmpImporter(preferencesService.getXmpPreferences()).importDatabase(file, StandardCharsets.UTF_8);
    }

    public ParserResult importFromBibFile(Path bibFile, FileUpdateMonitor fileUpdateMonitor) throws IOException {
        return OpenDatabase.loadDatabase(bibFile, preferencesService, timestampPreferences, fileUpdateMonitor);
    }
}
