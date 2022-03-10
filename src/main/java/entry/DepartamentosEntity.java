package entry;

import javax.persistence.*;

/**
 * entry
 * Nombre_project: CIDE
 * DepartamentosEntity
 * Created by: sheng
 * Date : 10/02/2022
 * Description:
 **/
@Entity
@Table(name = "departamentos", schema = "cide", catalog = "")
public class DepartamentosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "iddepartamentos")
    private int iddepartamentos;
    @Basic
    @Column(name = "nombre_dept")
    private String nombreDept;

    public int getIddepartamentos() {
        return iddepartamentos;
    }

    public void setIddepartamentos(int iddepartamentos) {
        this.iddepartamentos = iddepartamentos;
    }

    public String getNombreDept() {
        return nombreDept;
    }

    public void setNombreDept(String nombreDept) {
        this.nombreDept = nombreDept;
    }

    @Override
    public String toString() {
        return "DepartamentosEntity{" +
                "iddepartamentos=" + iddepartamentos +
                ", nombreDept='" + nombreDept + '\'' +
                '}';
    }
}
