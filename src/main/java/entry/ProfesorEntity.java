package entry;

import javax.persistence.*;

/**
 * entry
 * Nombre_project: CIDE
 * ProfesorEntity
 * Created by: sheng
 * Date : 10/02/2022
 * Description:
 **/
@Entity
@Table(name = "profesor", schema = "cide", catalog = "")
public class ProfesorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idprofesor")
    private int idprofesor;
    @Basic
    @Column(name = "id_personas")
    private Integer idPersonas;
    @Basic
    @Column(name = "id_dept")
    private Integer idDept;

    public int getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(int idprofesor) {
        this.idprofesor = idprofesor;
    }

    public Integer getIdPersonas() {
        return idPersonas;
    }

    public void setIdPersonas(Integer idPersonas) {
        this.idPersonas = idPersonas;
    }

    public Integer getIdDept() {
        return idDept;
    }

    public void setIdDept(Integer idDept) {
        this.idDept = idDept;
    }


    public ProfesorEntity(){

    }

    public ProfesorEntity(int idPersonas){
        this.idPersonas = idPersonas;

    }


    @Override
    public String toString() {
        return "ProfesorEntity{" +
                "idprofesor=" + idprofesor +
                ", idPersonas=" + idPersonas +
                ", idDept=" + idDept +
                '}';
    }
}
