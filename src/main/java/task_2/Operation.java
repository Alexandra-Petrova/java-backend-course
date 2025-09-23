package task_2;

public abstract class Operation {
    double a, b;

    public Operation (double a, double b) {
        this.a = a;
        this.b = b;
    }

    public abstract double calculate();
}

class Sum extends Operation {
    public Sum(double a, double b) {
        super(a, b);
    }

    @Override
    public double calculate() {
        return a + b;
    }
}

class Subtract extends Operation {
    public Subtract(double a, double b) {
        super(a, b);
    }

    @Override
    public double calculate() {
        return a - b;
    }
}

class Multiply extends Operation{
    public Multiply(double a, double b) {
        super(a, b);
    }

    @Override
    public double calculate() {
        return a * b;
    }
}

class Divide extends Operation {
    public Divide(double a, double b) {
        super(a, b);
    }

    @Override
    public double calculate() {
        if (b == 0) {
            return Double.NaN;
        } else {
            return a / b;
        }
    }
}

class Pow extends Operation {
    public Pow(double a, double b) {
        super(a, b);
    }

    @Override
    public double calculate() {
        return Math.pow(a, b);
    }
}

class IntegerDivide extends Operation {
    public IntegerDivide(double a, double b) {
        super (a, b);
    }

    @Override
    public double calculate() {
        if (b == 0) {
            return Double.NaN;
        } else {
            return Math.floor(a / b);
        }
    }
}

class Module extends Operation {
    public Module(double a, double b) {
        super(a, b);
    }

    @Override
    public double calculate() {
        return a % b;
    }
}
