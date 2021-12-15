package web4.Validator;

import org.springframework.stereotype.Service;
import web4.models.Point;

@Service
public class PointsValidator {
    public static boolean isValid(double x, double y, double r) {
        return (x >= -4 && x <= 4) && (y >= -3 && y <= 3) && (r >= 0 && r <= 4);
    }

    public boolean validate(Point point) {
        try {
            if (point.getX() != null && point.getY() != null && point.getR() != null) {
                if (point.getX().length() > 10) {
                    point.setX(point.getX().substring(0, 10));
                }
                if (point.getY().length() > 10) {
                    point.setY(point.getY().substring(0, 10));
                }
                if (point.getR().length() > 10) {
                    point.setR(point.getR().substring(0, 10));
                }
                double x = Double.parseDouble(point.getX().replace(",", "."));
                double y = Double.parseDouble(point.getY().replace(",", "."));
                double r = Double.parseDouble(point.getR().replace(",", "."));
                if (isValid(x, y, r)) {
                    point.setX(String.valueOf(x));
                    point.setY(String.valueOf(y));
                    point.setR(String.valueOf(r));
                    point.setResult(checkArea(x, y, r) ? "Да" : "Нет");
                    return true;
                }
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public boolean checkArea(double x, double y, double r) {
        return (checkRectangle(x, y, r) || checkTriangle(x, y, r) || checkCircle(x, y, r));
    }

    public boolean checkRectangle(double x, double y, double r) {
        return x <= 0 && y >= 0 && x >= -r && y <= r;
    }

    public boolean checkTriangle(double x, double y, double r) {
        return x >= 0 && y <= 0 && y >= x - r / 2;
    }

    public boolean checkCircle(double x, double y, double r) {
        return x >= 0 && y >= 0 && x * x + y * y <= r * r;
    }
}
