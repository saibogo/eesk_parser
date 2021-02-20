package support_classes;

public enum ExitStatus {
    OK {
        @Override
        public String toString() {
            return "Выполнено успешно!";
        }
    },
    NO_NETWORK {
        @Override
        public String toString() {
            return "Сайт недоступен или отсутствует сетевое соединение!";
        }
    },
    ERROR_OPEN_FILE {
        @Override
        public String toString() {
            return "Ошибка открытия файла!";
        }
    },
    ERROR_FILE_FORMAT {
        @Override
        public String toString() {
            return "Неверный формат файла!";
        }
    },
    ERROR_SAVE_FILE {
        @Override
        public String toString() {
            return "Ошибка сохранения файла!";
        }
    }
}
