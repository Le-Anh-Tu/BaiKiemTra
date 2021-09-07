    package Codegym;

    import java.io.*;
    import java.util.Arrays;
    import java.util.List;
    import java.util.Scanner;

    import static Codegym.Main.scanner;

    public class MenuProduct {
        public static Scanner scanner = new Scanner(System.in);
        public static ProductManagement productManagement = new ProductManagement();

        static {
            productManagement.addNewProduct(new Product("001", "Tivi", 10, 1, "Hình tam giác"));
            productManagement.addNewProduct(new Product("002", "Tủ Lạnh", 15.5, 2, "Hình chữ nhật"));
            productManagement.addNewProduct(new Product("003", "Nồi cơm", 5, 3, "Hình vuông"));
            productManagement.addNewProduct(new Product("004", "Lồng Bàn", 1, 4, "Chữ nhật"));
            productManagement.addNewProduct(new Product("005", "Bát", 6, 5, "Dài"));
            productManagement.addNewProduct(new Product("006", "Đũa", 12, 6, "tròn"));
        }

        public void run() {
            int choice;
            do {
                menuProduct();
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: {
                        showAllProduct(productManagement);
                        break;
                    }
                    case 2: {
                        addNewProduct(productManagement);
                        break;
                    }
                    case 3: {
                        updateProductById(productManagement);
                        break;
                    }
                    case 4: {
                        deleteProduct(productManagement);
                        break;
                    }
                    case 5: {
                        sortProduct(productManagement);
                        break;
                    }
                    case 6: {
                        productToPriceMax(productManagement);
                        break;
                    }
                    case 7: {
                        readDataToFile(productManagement);
                        break;
                    }
                    case 8: {
                        writeDataToFile(productManagement);
                        break;
                    }
                    case 9: {
                        System.out.println("OUT");
                    }
                    default: {
                        System.out.println("Không hợp lệ");
                    }
                }
            } while (choice != 9);
        }

        private void writeDataToFile(ProductManagement productManagement) {
            List<Product> products = productManagement.getProducts();
            try {
                System.out.print("Bạn muốn ghi vào file nào: ");
                String filePath = scanner.nextLine();
                File file = new File(filePath);
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                for (Product product : products) {
                    bw.write(String.valueOf(product));
                    bw.newLine();
                }
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void readDataToFile(ProductManagement productManagement) {
            try {
                System.out.print("Nhập vào file cần đọc: ");
                String filePath = scanner.nextLine();
                File file = new File(filePath);
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(Arrays.toString(line.split(",")));
                }
                br.close();
            } catch (IOException ie) {
                System.err.println("Fie không tồn tại or nội dung có lỗi!");
            }
        }

        private void productToPriceMax(ProductManagement productManagement) {
            int index = productManagement.findByProductToPriceMax();
            System.out.println("Sản phẩm có giá đắt nhất!");
            System.out.println(productManagement.getProducts().get(index));
        }

        private void sortProduct(ProductManagement productManagement) {
            int choice;
            do {
                System.out.println("1. Sắp xếp sản phẩm theo giá tăng dần");
                System.out.println("2. Hiển thị danh sách sau khi đã sắp xếp");
                System.out.println("3. Quay lại");
                System.out.println("Chọn: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1: {
                        productManagement.findByProductToPriceMax();
                        break;
                    }
                    case 2: {
                        productManagement.showAll();
                        break;
                    }
                    case 3: {
                        System.out.println("Bạn sẽ được đưa về Menu chính");
                    }
                }
            } while (choice != 0);
        }

        private static void deleteProduct(ProductManagement productManagement) {
            System.out.println("Xóa 1 sản phẩm");
            System.out.println("Nhập mã san pham cần tìm:");
            String id = scanner.nextLine();
            int index = productManagement.findProductById(id);
            if (index != -1) {
                System.out.println("Bạn có muốn xóa sản phẩm khỏi danh sách k??");
                System.out.println("Bấm T để xóa, Bấm phím bất kỳ để thoát");
                String Ktr = scanner.nextLine();
                if (Ktr.equals("T")) {
                    productManagement.removeProductById(id);
                    System.out.println("Xong");
                }
            } else {
                if (id.equals(""))
                    System.out.println("Không tồn tại id này");
                return;
            }
            System.out.println("Mời bạn nhập lại");
            deleteProduct(productManagement);
        }

        private static void updateProductById(ProductManagement productManagement) {

            System.out.println("Chỉnh sửa thông tin");
            System.out.println("Nhập mã sản phẩm cần chỉnh sửa");
            String id = scanner.nextLine();
            int index = productManagement.findProductById(id);
            if (index != -1) {
                Product product = inputProduct();
                productManagement.updateProductById(id, product);
            } else {
                if (id.equals("")) {
                    System.out.println("Không tồn tại id này");
                    return;
                }
                System.out.println("Mời bạn nhập lại");
                updateProductById(productManagement);
            }
        }

        private static void addNewProduct(ProductManagement productManagement) {
            Product product = inputProduct();
            productManagement.addNewProduct(product);
            System.out.println("Thêm mới thành công");

        }

        private static void showAllProduct(ProductManagement productManagement) {
            productManagement.showAll();
        }


        private static Product inputProduct() {
            int count = 0;
            do {
                if (count > 0) {
                    System.out.println("Bạn nhập sai rồi:");
                }
                System.out.println("Nhập mã sản phẩm:");
                String id = scanner.nextLine();
                System.out.println("Nhập tên sản phẩm:");
                String name = scanner.nextLine();
                System.out.println("Nhập giá sản phẩm:");
                double price = scanner.nextInt();
                System.out.println("Nhập số lượng:");
                int quantity = scanner.nextInt();
                System.out.println("Nhập moo tả:");
                String description = scanner.nextLine();
                count++;
                return new Product(id, name, price, quantity, description);
            } while (count == 0);
        }

        public static void menuProduct() {
            System.out.println("-----CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM-----");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp");
            System.out.println("6.Tìm sản phẩm có giá đắt nhất");
            System.out.println("7. Đọc từ file");
            System.out.println("8. Ghi từ file");
            System.out.println("9. Thoát");
            System.out.print("Chọn chứ năng: ");
        }
    }
