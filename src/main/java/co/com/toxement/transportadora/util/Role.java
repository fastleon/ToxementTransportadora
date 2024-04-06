package co.com.toxement.transportadora.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    CUSTOMER(Arrays.asList(Permisos.READ_ALL_PRODUCTOS)),
    ADMINISTRATOR(Arrays.asList(Permisos.SAVE_ONE_PRODUCTO, Permisos.READ_ALL_PRODUCTOS));

    private List<Permisos> permisos;

    Role(List<Permisos> permisos) {
        this.permisos = permisos;
    }

    public List<Permisos> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permisos> permisos) {
        this.permisos = permisos;
    }
}
