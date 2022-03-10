package entry;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * entry
 * Nombre_project: CIDE
 * PersonasEntity
 * Created by: sheng
 * Date : 10/02/2022
 * Description:
 **/
@Entity
@Table(name = "personas", schema = "cide")
public class PersonasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_personas")
    private int idPersonas;
    @Basic
    @Column(name = "nif")
    private String nif;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Basic
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic
    @Column(name = "fecha_nacimiento")
    private Timestamp fechaNacimiento;
    @Basic
    @Column(name = "direccion")
    private String direccion;
    @Basic
    @Column(name = "sexo")
    private String sexo;
    @Basic
    @Column(name = "num_telefono")
    private Integer numTelefono;


    public PersonasEntity(){

    }

    public PersonasEntity(int idPersonas){
        this.idPersonas = idPersonas;

    }

    public PersonasEntity(String nombre){
        this.nombre = nombre;

    }



    public int getIdPersonas() {
        return idPersonas;
    }

    public void setIdPersonas(int idPersonas) {
        this.idPersonas = idPersonas;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(Integer numTelefono) {
        this.numTelefono = numTelefono;
    }

    @Override
    public String toString() {
        Date date = new Date();
        date.setTime(fechaNacimiento.getTime());
        String formatted = new SimpleDateFormat("dd-MM-yyyy").format(date);
        return "Personas: " +
                "[idPersonas=" + idPersonas +
                ", nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                "] fechaNacimiento=" + formatted +
                ", direccion='" + direccion + '\'' +
                ", sexo='" + sexo + '\'' +
                ", numTelefono=" + numTelefono +
                  "\n";
    }
}
