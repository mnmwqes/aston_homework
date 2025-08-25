class Computer {
    private String cpu;
    private String gpu;
    private int ram;

    public static class Builder {
        private String cpu;
        private String gpu;
        private int ram;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Computer build() {
            Computer c = new Computer();
            c.cpu = this.cpu;
            c.gpu = this.gpu;
            c.ram = this.ram;
            return c;
        }
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", GPU=" + gpu + ", RAM=" + ram + "GB]";
    }
}

public class Builder {
    public static void main(String[] args) {
        Computer pc = new Computer.Builder()
                .setCpu("Intel i5")
                .setGpu("NVIDIA GTX 1650")
                .setRam(16)
                .build();

        System.out.println(pc);
    }
}
