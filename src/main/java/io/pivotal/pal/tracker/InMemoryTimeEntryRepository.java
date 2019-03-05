package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    ArrayList<TimeEntry> entriesList = new ArrayList<TimeEntry>();
    private int counter = 1;

    public TimeEntry find(long id) {

        for (TimeEntry timeEntry: entriesList) {
            if (timeEntry.getId() == id) {
                return timeEntry;
            }
        }
        return null;
    }

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry returnTimeEntry = new TimeEntry(counter++, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        entriesList.add(returnTimeEntry);
        return returnTimeEntry;
    }

    public List<TimeEntry> list() {
        return entriesList;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry foundTimeEntry = find(id);
        if (foundTimeEntry != null) {
            foundTimeEntry.setDate(timeEntry.getDate());
            foundTimeEntry.setHours(timeEntry.getHours());
            foundTimeEntry.setProjectId(timeEntry.getProjectId());
            foundTimeEntry.setUserId(timeEntry.getUserId());
            return foundTimeEntry;
        }
        return null;
    }

    public void delete(long id) {
        TimeEntry foundTimeEntry = find(id);
        entriesList.remove(foundTimeEntry);
    }


}
