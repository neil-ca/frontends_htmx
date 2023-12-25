package com.modernfrontendshtmx.contact.service;

import com.modernfrontendshtmx.contact.Contact;
import com.modernfrontendshtmx.contact.repository.ContactRepository;
import com.modernfrontendshtmx.contact.repository.Page;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Archiver {
    private final ExecutorService executorService;
    private final ContactRepository contactRepository;
    private final Map<ArchiveId, ArchiveProcessInfo> archives = new HashMap<>();

    public ArchiveId startArchiving() {
        ArchiveId id = new ArchiveId(UUID.randomUUID());
        archives.put(id, new ArchiveProcessInfo());
        Future<String> future = executorService.submit(() -> {
            try {
                StringBuilder builder = new StringBuilder();
                addCsvHeader(builder);
                Page<Contact> page;
                int currentPage = 0;
                do {
                    page = contactRepository.findAllOrderedByName(currentPage, 10);
                    for (Contact contact : page.values()) {
                        addCsvRow(builder, contact);
                    }

                    int elementsArchived = (page.number() + 1) * page.size();
                    int progress = (int) ((double) elementsArchived / page.totalElements() * 100);
                    archives.get(id).setProgress(progress);
                    currentPage++;

                    // Fake some work to be able to hve a nice progress bar
                    Thread.sleep(200);
                } while ((page.number() + 1) * page.size() < page.totalElements());
                archives.get(id).setStatus(ArchiveProcessInfo.Status.COMPLETE);
                return builder.toString();
            } catch (Exception e) {
                archives.get(id).setStatus(ArchiveProcessInfo.Status.FAILED);
                return null;
            }
        });
        archives.get(id).setFuture(future);
        return id;
    }

    public ArchiveProcessInfo getArchiveProcessInfo(ArchiveId archiveId) {
        return archives.get(archiveId);
    }

    private static void addCsvHeader(StringBuilder builder) {
        builder.append("Given name, Family name, Email, Phone")
                .append(System.lineSeparator());
    }

    private static void addCsvRow(StringBuilder builder, Contact contact) {
        builder.append(contact.getGivenName())
                .append(",")
                .append(contact.getFamilyName())
                .append(",")
                .append(contact.getEmail())
                .append(",")
                .append(contact.getPhone())
                .append(System.lineSeparator());
    }
}
