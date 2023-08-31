package Models;

public class Device  {
 
        private  int id;
        private  String name;
        private  String description;
        private  String importDate;
        private  int quantity;
        private  Category category;
        private  Status status;


        public Device(int id, String name, String description, String importDate,  int quantity, Category category, Status status) {
            this.id = id;

            this.name = name;
            this.description = description;
            this.importDate = importDate;
            this.quantity = quantity;
            this.category = category;
            this.status = status;
        }

        public Device() {
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImportDate() {
            return importDate;
        }

        public void setImportDate(String importDate) {
            this.importDate = importDate;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Device{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", importDate='" + importDate + '\'' +
                    ", quantity=" + quantity +
                    ", category=" + category +
                    ", status=" + status +

                    '}';
        }
    }

