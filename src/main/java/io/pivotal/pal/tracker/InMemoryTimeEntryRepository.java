package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> dataStorage = new HashMap<>();
    private long currentId = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        long id = currentId++;
        TimeEntry newEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        dataStorage.put(id, newEntry);

        return newEntry;
    }

    public TimeEntry find(long id) {
        return dataStorage.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(dataStorage.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(find(id) == null) return null;

        TimeEntry updateEntry = dataStorage.get(id);

        updateEntry.setProjectId(timeEntry.getProjectId());
        updateEntry.setUserId(timeEntry.getUserId());
        updateEntry.setDate(timeEntry.getDate());
        updateEntry.setHours(timeEntry.getHours());

        return updateEntry;
    }

    public void delete(long id) {
        dataStorage.remove(id);
    }
}
