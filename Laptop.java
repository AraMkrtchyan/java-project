import java.util.*;

public class Laptop {
    private String model;
    private int ram;
    private int hdd;
    private String os;
    private String color;

    public Laptop(String model, int ram, int hdd, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getHdd() {
        return hdd;
    }

    public void setHdd(int hdd) {
        this.hdd = hdd;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Model 1", 8, 512, "Windows 10", "Black"));
        laptops.add(new Laptop("Model 2", 16, 1024, "MacOS", "Silver"));
        laptops.add(new Laptop("Model 3", 8, 256, "Windows 10", "Black"));

        Map<Integer, Object> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number corresponding to the filter criterion:");
        System.out.println("1 - RAM");
        System.out.println("2 - HDD");
        System.out.println("3 - Operating System");
        System.out.println("4 - Color");

        int criterion = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the minimum value for the selected criterion:");

        Object value = null;
        switch (criterion) {
            case 1:
                int minRam = scanner.nextInt();
                scanner.nextLine();
                filters.put(criterion, minRam);
                break;
            case 2:
                int minHdd = scanner.nextInt();
                scanner.nextLine();
                filters.put(criterion, minHdd);
                break;
            case 3:
                String minOs = scanner.nextLine();
                filters.put(criterion, minOs);
                break;
            case 4:
                String minColor = scanner.nextLine();
                filters.put(criterion, minColor);
                break;
        }

        List<Laptop> filteredLaptops = filterLaptops(laptops, filters);

        if (filteredLaptops.isEmpty()) {
            System.out.println("No laptops match the filter criteria.");
        } else {
            System.out.println("Filtered Laptops:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop.getModel());
            }
        }
    }

    public static List<Laptop> filterLaptops(Set<Laptop> laptops, Map<Integer, Object> filters) {
        List<Laptop> filteredLaptops = new ArrayList<>();

        for (Laptop laptop : laptops) {
            boolean matches = true;

            for (Map.Entry<Integer, Object> entry : filters.entrySet()) {
                int criterion = entry.getKey();
                Object value = entry.getValue();

                switch (criterion) {
                    case 1:
                        int minRam = (int) value;
                        if (laptop.getRam() < minRam) {
                            matches = false;
                        }
                        break;
                    case 2:
                        int minHdd = (int) value;
                        if (laptop.getHdd() < minHdd) {
                            matches = false;
                        }
                        break;
                    case 3:
                        String minOs = (String) value;
                        if (!laptop.getOs().equals(minOs)) {
                            matches = false;
                        }
                        break;
                    case 4:
                        String minColor = (String) value;
                        if (!laptop.getColor().equals(minColor)) {
                            matches = false;
                        }
                        break;
                }

                if (!matches) {
                    break;
                }
            }

            if (matches) {
                filteredLaptops.add(laptop);
            }
        }

        return filteredLaptops;
    }
}