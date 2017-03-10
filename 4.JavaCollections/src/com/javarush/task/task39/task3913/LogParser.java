package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.EventQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery {
    //Fields
    private Path logDir;
    private List<String> linesList;

    //Constructor
    public LogParser(Path logDir) {
        this.logDir = logDir;
        linesList = getLinesList();
    }

    //Functions
    private List<String> getLinesList() {
        String[] files = logDir.toFile().list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".log");
            }
        });

        List<String> lines = new ArrayList<>();
        for (String file : files) {
            try {
                lines.addAll(Files.readAllLines(Paths.get(logDir + File.separator + file), Charset.defaultCharset()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    private void addStringEntity(Date after, Date before, Set<String> enteties, String[] parts, int part) {
        long lineDateTime = getDate(parts[2]).getTime();

        if (isCompatibleDate(lineDateTime, after, before)) {
            enteties.add(parts[part]);
        }
    }

    private boolean isCompatibleDate(long lineDateTime, Date after, Date before) {
        if (after == null && before == null) {
            return true;
        } else if (after == null) {
            if (lineDateTime <= before.getTime()) {
                return true;
            }
        } else if (before == null) {
            if (lineDateTime >= after.getTime()) {
                return true;
            }
        } else {
            if (lineDateTime >= after.getTime() && lineDateTime <= before.getTime()) {
                return true;
            }
        }
        return false;
    }

    private Date getDate(String part) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(part);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private void addDateEntity(Date after, Date before, Set<Date> dates, String[] parts) {
        Date date = getDate(parts[2]);
        long dateTime = getDate(parts[2]).getTime();
        if (isCompatibleDate(dateTime, after, before)) {
            dates.add(date);
        }
    }

    private void addEventEntity(Date after, Date before, Set<Event> enteties, String[] parts) {
        Event event = Event.valueOf(parts[3].split(" ")[0]);
        long lineDateTime = getDate(parts[2]).getTime();

        if (isCompatibleDate(lineDateTime, after, before)) {
            enteties.add(event);
        }
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> uniqueIPs = new HashSet<>();

        for (String line : linesList) {
            String[] parts = line.split("\\t");

            addStringEntity(after, before, uniqueIPs, parts, 0);
        }
        return uniqueIPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> IPsForUser = new HashSet<>();

        for (String line : linesList) {
            String[] parts = line.split("\\t");

            if (user.equals(parts[1])) {
                addStringEntity(after, before, IPsForUser, parts, 0);
            }
        }
        return IPsForUser;
    }


    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> IPsForEvent = new HashSet<>();

        for (String line : linesList) {
            String[] parts = line.split("\\t");

            if (event.toString().equals(parts[3].split(" ")[0])) {
                addStringEntity(after, before, IPsForEvent, parts, 0);
            }
        }
        return IPsForEvent;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> IPsForEvent = new HashSet<>();

        for (String line : linesList) {
            String[] parts = line.split("\\t");

            if (status.toString().equals(parts[4])) {
                addStringEntity(after, before, IPsForEvent, parts, 0);
            }
        }
        return IPsForEvent;
    }

    @Override
    public Set<String> getAllUsers() {//должен возвращать всех пользователей
        Set<String> allUsers = new HashSet<>();
        for (String line : linesList) {
            allUsers.add(line.split("\t")[1]);
        }
        return allUsers;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {//должен возвращать количество уникальных пользователей
        Set<String> users = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            addStringEntity(after, before, users, parts, 1);
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {//должен возвращать количество событий от определенного пользователя
        Set<String> events = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1])) {
                addStringEntity(after, before, events, parts, 3);
            }
        }
        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {//должен возвращать пользователей с определенным IP
        Set<String> users = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (ip.equals(parts[0])) {
                addStringEntity(after, before, users, parts, 1);
            }
        }
        return users;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {//должен возвращать пользователей, которые были залогинены
        Set<String> users = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.LOGIN.toString().equals(parts[3])) {
                addStringEntity(after, before, users, parts, 1);
            }
        }
        return users;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {//должен возвращать пользователей, которые скачали плагин
        Set<String> users = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.DOWNLOAD_PLUGIN.toString().equals(parts[3])) {
                addStringEntity(after, before, users, parts, 1);
            }
        }
        return users;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {//должен возвращать пользователей, которые отправили сообщение
        Set<String> users = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.WRITE_MESSAGE.toString().equals(parts[3])) {
                addStringEntity(after, before, users, parts, 1);
            }
        }
        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {//должен возвращать пользователей, которые решали любую задачу
        Set<String> users = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0])) {
                addStringEntity(after, before, users, parts, 1);
            }
        }
        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {//должен возвращать пользователей, которые решали задачу с номером task
        Set<String> users = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0]) && task == Integer.parseInt(parts[3].split(" ")[1])) {
                addStringEntity(after, before, users, parts, 1);
            }
        }
        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {//должен возвращать пользователей, которые решали любую задачу
        Set<String> users = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0])) {
                addStringEntity(after, before, users, parts, 1);
            }
        }
        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {//должен возвращать пользователей, которые решали задачу с номером task
        Set<String> users = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0]) && task == Integer.parseInt(parts[3].split(" ")[1])) {
                addStringEntity(after, before, users, parts, 1);
            }
        }
        return users;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {//должен возвращать даты, когда определенный пользователь произвел определенное событие
        Set<Date> dates = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) && event.toString().equals(parts[3].split(" ")[0])) {
                addDateEntity(after, before, dates, parts);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {//должен возвращать даты, когда любое событие не выполнилось (статус FAILED)
        Set<Date> dates = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Status.FAILED.toString().equals(parts[4])) {
                addDateEntity(after, before, dates, parts);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {//должен возвращать даты, когда любое событие закончилось ошибкой (статус ERROR)
        Set<Date> dates = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Status.ERROR.toString().equals(parts[4])) {
                addDateEntity(after, before, dates, parts);
            }
        }
        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {//должен возвращать дату, когда пользователь залогинился впервые за указанный период. Если такой даты в логах нет — null
        TreeSet<Date> date = new TreeSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) && Event.LOGIN.toString().equals(parts[3]) && Status.OK.toString().equals(parts[4])) {
                if (isCompatibleDate(getDate(parts[2]).getTime(), after, before)) {
                    date.add(getDate(parts[2]));
                }
            }
        }
        return !date.isEmpty() ? date.first() : null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {//должен возвращать дату, когда пользователь впервые попытался решить определенную задачу. Если такой даты в логах нет — null
        TreeSet<Date> date = new TreeSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) && Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0]) && task == Integer.parseInt(parts[3].split(" ")[1])) {
                if (isCompatibleDate(getDate(parts[2]).getTime(), after, before)) {
                    date.add(getDate(parts[2]));
                }
            }
        }
        return !date.isEmpty() ? date.first() : null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {//должен возвращать дату, когда пользователь впервые решил определенную задачу. Если такой даты в логах нет — null
        TreeSet<Date> date = new TreeSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) && Event.DONE_TASK.toString().equals(parts[3].split(" ")[0]) && task == Integer.parseInt(parts[3].split(" ")[1])) {
                if (isCompatibleDate(getDate(parts[2]).getTime(), after, before)) {
                    date.add(getDate(parts[2]));
                }
            }
        }
        return !date.isEmpty() ? date.first() : null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {//должен возвращать даты, когда пользователь написал сообщение
        Set<Date> dates = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) && Event.WRITE_MESSAGE.toString().equals(parts[3])) {
                addDateEntity(after, before, dates, parts);
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {//должен возвращать даты, когда пользователь скачал плагин
        Set<Date> dates = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1]) && Event.DOWNLOAD_PLUGIN.toString().equals(parts[3])) {
                addDateEntity(after, before, dates, parts);
            }
        }
        return dates;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {//должен возвращать количество событий за указанный период
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {//должен возвращать все события за указанный период
        Set<Event> events = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            addEventEntity(after, before, events, parts);
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {// должен возвращать события, которые происходили с указанного IP
        Set<Event> events = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (ip.equals(parts[0])) {
                addEventEntity(after, before, events, parts);
            }
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {// должен возвращать события, которые инициировал определенный пользователь
        Set<Event> events = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (user.equals(parts[1])) {
                addEventEntity(after, before, events, parts);
            }
        }
        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {//должен возвращать события, которые не выполнились
        Set<Event> events = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (parts[4].equals(Status.FAILED.toString())) {
                addEventEntity(after, before, events, parts);
            }
        }
        return events;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {//должен возвращать события, которые завершились ошибкой
        Set<Event> events = new HashSet<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (parts[4].equals(Status.ERROR.toString())) {
                addEventEntity(after, before, events, parts);
            }
        }
        return events;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {// должен возвращать количество попыток решить определенную задачу
        int countAttempt = 0;
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0]) && Integer.parseInt(parts[3].split(" ")[1]) == task) {
                if (isCompatibleDate(getDate(parts[2]).getTime(), after, before)) {
                    countAttempt++;
                }
            }
        }
        return countAttempt;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {//должен возвращать количество успешных решений определенной задачи
        int numberOfSuccessfulAttemptToSolveTask = 0;

        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0])
                    && task == Integer.parseInt(parts[3].split(" ")[1])) {
                if (isCompatibleDate(getDate(parts[2]).getTime(), after, before)) {
                    numberOfSuccessfulAttemptToSolveTask++;
                }
            }
        }
        return numberOfSuccessfulAttemptToSolveTask;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {//должен возвращать мапу (номер_задачи : количество_попыток_решить_ее)
        Map<Integer, Integer> solvedTasks = new HashMap<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.SOLVE_TASK.toString().equals(parts[3].split(" ")[0])) {
                if (isCompatibleDate(getDate(parts[2]).getTime(), after, before)) {
                    if (solvedTasks.containsKey(Integer.parseInt(parts[3].split(" ")[1]))) {
                        continue;
                    } else {
                        solvedTasks.put(Integer.parseInt(parts[3].split(" ")[1]), getNumberOfAttemptToSolveTask(Integer.parseInt(parts[3].split(" ")[1]), after, before));
                    }
                }
            }
        }
        return solvedTasks;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {//должен возвращать мапу (номер_задачи : сколько_раз_ее_решили)
        Map<Integer, Integer> solvedTasks = new HashMap<>();
        for (String line : linesList) {
            String[] parts = line.split("\\t");
            if (Event.DONE_TASK.toString().equals(parts[3].split(" ")[0])) {
                if (isCompatibleDate(getDate(parts[2]).getTime(), after, before)) {
                    if (solvedTasks.containsKey(Integer.parseInt(parts[3].split(" ")[1]))) {
                        continue;
                    } else {
                        solvedTasks.put(Integer.parseInt(parts[3].split(" ")[1]), getNumberOfSuccessfulAttemptToSolveTask(Integer.parseInt(parts[3].split(" ")[1]), after, before));
                    }
                }
            }
        }
        return solvedTasks;
    }
}