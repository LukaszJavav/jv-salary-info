package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate start = LocalDate.parse(dateFrom.trim(), FORMATTER);
        LocalDate end = LocalDate.parse(dateTo.trim(), FORMATTER);
        StringBuilder builder = new StringBuilder();

        builder.append("Report for period ")
                .append(start.format(FORMATTER))
                .append(" - ")
                .append(end.format(FORMATTER))
                .append("\n");
        for (String name : names) {
            int salary = 0;

            for (int i = 0; i < data.length; i++) {
                String[] parts = data[i].split(" ");
                LocalDate localDate1 = LocalDate.parse(parts[0], FORMATTER);
                String employeeName = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int income = Integer.parseInt(parts[3]);
                if (employeeName.equals(name) && !localDate1.isBefore(start) && !localDate1.isAfter(end)) {
                    salary += hours * income;
                }
            }
            builder.append(name)
                    .append(" - ")
                    .append(salary)
                    .append("\n");
        }
        return builder.toString();
    }
}
