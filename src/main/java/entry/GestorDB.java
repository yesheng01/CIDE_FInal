package entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.xml.xpath.XPathExpressionException;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * entry
 * Nombre_project: CIDE
 * GestorDB
 * Created by: sheng
 * Date : 10/02/2022
 * Description:
 **/
public class GestorDB {
    //Asignamos el session para poder realizar la conexion con la base de datos
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();

    //Atributos al que utilizaremos
    PersonasEntity persona;
    int profesor;
    int per;

    ProfesorEntity pr;
    DepartamentosEntity dep;
    int depa;


    public static void main(String[] args) throws XPathExpressionException, FileNotFoundException {
        GestorDB gestorDB = new GestorDB();
        gestorDB.menu2();
    }


    //personas


    public void eliminar_personas() {
        session.beginTransaction();

        try {

            PersonasEntity personasEntity = new PersonasEntity();

            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Inserta el id para eliminar");
            int id_persona = scanner1.nextInt();
            personasEntity.setIdPersonas(id_persona);

            session.delete(personasEntity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();


    }


    public void add_personas() {
        session.beginTransaction();

        try {

            PersonasEntity personasEntity = new PersonasEntity();

            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Inserta el nif");
            String nif = scanner1.nextLine();
            personasEntity.setNif(nif);

            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Inserta el nombre");
            String nombre = scanner2.nextLine();
            personasEntity.setNombre(nombre);


            Scanner scanner3 = new Scanner(System.in);
            System.out.println("Inserta el primer apellido");
            String apellido1 = scanner3.nextLine();
            personasEntity.setPrimerApellido(apellido1);

            Scanner scanner4 = new Scanner(System.in);
            System.out.println("Inserta el segundo apellido");
            String apellido2 = scanner4.nextLine();
            personasEntity.setSegundoApellido(apellido2);


            Scanner scanner5 = new Scanner(System.in);
            System.out.println("Inserta el direccion");
            String direccion = scanner5.nextLine();
            personasEntity.setDireccion(direccion);

            Scanner scanner6 = new Scanner(System.in);
            System.out.println("Inserta la fecha de nacimiento");
            String fecha = scanner6.nextLine();
            DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDateTime = LocalDate.parse(fecha, formatDateTime);
            Timestamp ts = Timestamp.valueOf(localDateTime.atStartOfDay());
            personasEntity.setFechaNacimiento(ts);


            Scanner scanner7 = new Scanner(System.in);
            System.out.println("Inserta el sexo");
            String sexo = scanner7.nextLine();
            personasEntity.setSexo(sexo);

            Scanner scanner8 = new Scanner(System.in);
            System.out.println("Inserta el telefono");
            int telefono = scanner8.nextInt();
            personasEntity.setNumTelefono(telefono);

            session.saveOrUpdate(personasEntity);


        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }


    public void modificar_personas() {
        session.beginTransaction();
        try {


            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Inserta el id para modificar");
            int id_personas = scanner1.nextInt();
            PersonasEntity personasEntity = session.get(PersonasEntity.class, id_personas);


            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Inserta el nif para modificar");
            String nif_personas = scanner2.nextLine();

            personasEntity.setNif(nif_personas);


            Scanner scanner3 = new Scanner(System.in);
            System.out.println("Inserta el nombre para modificar");
            String nombre_personas = scanner3.nextLine();

            personasEntity.setNombre(nombre_personas);


            Scanner scanner4 = new Scanner(System.in);
            System.out.println("Inserta el apellido1 para modificar");
            String apellido1_personas = scanner4.nextLine();

            personasEntity.setPrimerApellido(apellido1_personas);

            Scanner scanner5 = new Scanner(System.in);
            System.out.println("Inserta el apellido2 para modificar");
            String apellido2_personas = scanner5.nextLine();

            personasEntity.setSegundoApellido(apellido2_personas);


            Scanner scanner6 = new Scanner(System.in);
            System.out.println("Inserta el direccion para modificar");
            String direccion_personas = scanner6.nextLine();

            personasEntity.setDireccion(direccion_personas);


            Scanner scanner7 = new Scanner(System.in);
            System.out.println("Inserta la fecha de nacimiento");
            String fecha = scanner7.nextLine();
            DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDateTime = LocalDate.parse(fecha, formatDateTime);
            Timestamp ts = Timestamp.valueOf(localDateTime.atStartOfDay());
            personasEntity.setFechaNacimiento(ts);


            Scanner scanner8 = new Scanner(System.in);
            System.out.println("Inserta el sexo");
            String sexo = scanner8.nextLine();

            personasEntity.setSexo(sexo);


            Scanner scanner9 = new Scanner(System.in);
            System.out.println("Inserta el numero de telefono");
            int tele_personas = scanner9.nextInt();

            personasEntity.setNumTelefono(tele_personas);

            session.update(personasEntity);


        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }


    public void buscarpersonas_porId() {
        session.beginTransaction();

        try {

            Scanner scanner12 = new Scanner(System.in);
            System.out.println("Inserta el id ");
            String busqueda = scanner12.nextLine();
            Query query = session.createQuery("from PersonasEntity where id = '" + busqueda + "'");
            List<PersonasEntity> personasEntitys = query.list();
            for (PersonasEntity s : personasEntitys) {
                persona = s;
                profesor = s.getIdPersonas();
                query = session.createQuery("from ProfesorEntity where idPersonas= '" + profesor + "'");
                List<ProfesorEntity> profesorE = query.list();
                for (ProfesorEntity profesor1 : profesorE) {
                    pr = profesor1;
                    depa = pr.getIdDept();

                    query = session.createQuery("from DepartamentosEntity where iddepartamentos= '" + depa + "'");
                    List<DepartamentosEntity> departamentosEntity = query.list();
                    for (DepartamentosEntity departamentos : departamentosEntity) {
                        dep = departamentos;
                        System.out.println(persona.toString() + "\n ID del profesor " + pr.getIdprofesor() + " Departamento al que pertenece: " + dep.getNombreDept() + "\n");
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }

    public void buscarpersonas_porNIF() {
        session.beginTransaction();

        try {

            Scanner scanner12 = new Scanner(System.in);
            System.out.println("Inserta el NIF ");
            String busqueda = scanner12.nextLine();
            Query query = session.createQuery("from PersonasEntity where nif = '" + busqueda + "'");
            List<PersonasEntity> personasEntitys = query.list();
            for (PersonasEntity s : personasEntitys) {
                persona = s;
                profesor = s.getIdPersonas();
                query = session.createQuery("from ProfesorEntity where idPersonas= '" + profesor + "'");
                List<ProfesorEntity> profesorE = query.list();
                for (ProfesorEntity profesor1 : profesorE) {
                    pr = profesor1;
                    depa = pr.getIdDept();

                    query = session.createQuery("from DepartamentosEntity where iddepartamentos= '" + depa + "'");
                    List<DepartamentosEntity> departamentosEntity = query.list();
                    for (DepartamentosEntity departamentos : departamentosEntity) {
                        dep = departamentos;
                        System.out.println(persona.toString() + "\n ID del profesor " + pr.getIdprofesor() + " Departamento al que pertenece: " + dep.getNombreDept() + "\n");
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();


    }





    public void buscarpersonas_porNombre() {
        session.beginTransaction();

        try {

            Scanner scanner12 = new Scanner(System.in);
            System.out.println("Inserta el nombre ");
            String busqueda = scanner12.nextLine();
            Query query = session.createQuery("from PersonasEntity where nombre like '%" + busqueda + "%'");
            List<PersonasEntity> personasEntitys = query.list();
            for (PersonasEntity s : personasEntitys) {
                persona = s;
                profesor = s.getIdPersonas();
                query = session.createQuery("from ProfesorEntity where idPersonas= '" + profesor + "'");
                List<ProfesorEntity> profesorE = query.list();
                for (ProfesorEntity profesor1 : profesorE) {
                    pr = profesor1;
                    depa = pr.getIdDept();

                    query = session.createQuery("from DepartamentosEntity where iddepartamentos= '" + depa + "'");
                    List<DepartamentosEntity> departamentosEntity = query.list();
                    for (DepartamentosEntity departamentos : departamentosEntity) {
                        dep = departamentos;
                        System.out.println(persona.toString() + "\n ID del profesor " + pr.getIdprofesor() + " Departamento al que pertenece: " + dep.getNombreDept() + "\n");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }
    public void buscarpersonas_porApellido1() {
        session.beginTransaction();

        try {

            Scanner scanner12 = new Scanner(System.in);
            System.out.println("Inserta el apellido1 ");
            String busqueda = scanner12.nextLine();
            Query query = session.createQuery("from PersonasEntity where primerApellido like '%" + busqueda + "%'");
            List<PersonasEntity> personasEntitys = query.list();
            for (PersonasEntity s : personasEntitys) {
                persona = s;
                profesor = s.getIdPersonas();
                query = session.createQuery("from ProfesorEntity where idPersonas= '" + profesor + "'");
                List<ProfesorEntity> profesorE = query.list();
                for (ProfesorEntity profesor1 : profesorE) {
                    pr = profesor1;
                    depa = pr.getIdDept();

                    query = session.createQuery("from DepartamentosEntity where iddepartamentos= '" + depa + "'");
                    List<DepartamentosEntity> departamentosEntity = query.list();
                    for (DepartamentosEntity departamentos : departamentosEntity) {
                        dep = departamentos;
                        System.out.println(persona.toString() + "\n ID del profesor " + pr.getIdprofesor() + " Departamento al que pertenece: " + dep.getNombreDept() + "\n");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }


    public void buscarpersonas_porApellido2() {
        session.beginTransaction();

        try {

            Scanner scanner12 = new Scanner(System.in);
            System.out.println("Inserta el apellido2 ");
            String busqueda = scanner12.nextLine();
            Query query = session.createQuery("from PersonasEntity where segundoApellido like '%" + busqueda + "%'");
            List<PersonasEntity> personasEntitys = query.list();
            for (PersonasEntity s : personasEntitys) {
                persona = s;
                profesor = s.getIdPersonas();
                query = session.createQuery("from ProfesorEntity where idPersonas= '" + profesor + "'");
                List<ProfesorEntity> profesorE = query.list();
                for (ProfesorEntity profesor1 : profesorE) {
                    pr = profesor1;
                    depa = pr.getIdDept();

                    query = session.createQuery("from DepartamentosEntity where iddepartamentos= '" + depa + "'");
                    List<DepartamentosEntity> departamentosEntity = query.list();
                    for (DepartamentosEntity departamentos : departamentosEntity) {
                        dep = departamentos;
                        System.out.println(persona.toString() + "\n ID del profesor " + pr.getIdprofesor() + " Departamento al que pertenece: " + dep.getNombreDept() + "\n");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }

    //-----------------------------------------------------------------------------------------------------------


    //Departamento
    public void add_dept() {
        session.beginTransaction();

        try {

            DepartamentosEntity departamentosEntity = new DepartamentosEntity();

            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Inserta el nombre de dept");
            String nom_dept = scanner1.nextLine();
            departamentosEntity.setNombreDept(nom_dept);

            session.saveOrUpdate(departamentosEntity);


        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }


    public void modificar_dept() {
        session.beginTransaction();

        try {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Inserta el id para modificar");
            int id_dept = scanner1.nextInt();
            DepartamentosEntity departamentosEntity = session.get(DepartamentosEntity.class, id_dept);


            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Inserta el nombre a modificar");
            String nom_dept = scanner2.nextLine();
            departamentosEntity.setNombreDept(nom_dept);


            session.update(departamentosEntity);


        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }

    public void eliminar_dept() {
        session.beginTransaction();

        try {

            DepartamentosEntity departamentosEntity = new DepartamentosEntity();

            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Inserta el id para eliminar");
            int id_dept = scanner1.nextInt();
            departamentosEntity.setIddepartamentos(id_dept);

            session.delete(departamentosEntity);


        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }


    public void buscardep_porid() {
        session.beginTransaction();

        try {

            Scanner scanner12 = new Scanner(System.in);
            System.out.println("Inserta el id ");
            String busqueda = scanner12.nextLine();
            Query query = session.createQuery("from DepartamentosEntity where id = '" + busqueda + "' ");
            List<DepartamentosEntity> departamentos = query.list();
            for (DepartamentosEntity s : departamentos) {
                dep = s;
                profesor = s.getIddepartamentos();

                query = session.createQuery("from ProfesorEntity where idDept= '" + profesor + "'");
                List<ProfesorEntity> profesorE = query.list();
                for (ProfesorEntity profesor1 : profesorE) {
                    pr = profesor1;
                    per = profesor1.getIdPersonas();

                    query = session.createQuery("from PersonasEntity where idPersonas= '" + per + "'");
                    List<PersonasEntity> perso = query.list();
                    for (PersonasEntity personasEntity : perso) {
                        persona = personasEntity;
                        System.out.println(persona.toString() + "\n ID del profesor " + pr.getIdprofesor() + " Departamento al que pertenece: " + dep.getNombreDept() + "\n");
                    }

//                        System.out.println(dep.getIddepartamentos() + " " + dep.getNombreDept() + " " + pr.getIdprofesor());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }


    public void buscardep_porNombre() {
        session.beginTransaction();

        try {


            Scanner scanner12 = new Scanner(System.in);
            System.out.println("Inserta el nombre");
            String busqueda = scanner12.nextLine();
            Query query = session.createQuery("from DepartamentosEntity where nombreDept like  '%" + busqueda + "%' ");
            List<DepartamentosEntity> departamentos = query.list();
            for (DepartamentosEntity s : departamentos) {
                dep = s;
                profesor = s.getIddepartamentos();

                query = session.createQuery("from ProfesorEntity where idDept= '" + profesor + "'");
                List<ProfesorEntity> profesorE = query.list();
                for (ProfesorEntity profesor1 : profesorE) {
                    pr = profesor1;
                    per = profesor1.getIdPersonas();

                    query = session.createQuery("from PersonasEntity where idPersonas= '" + per + "'");
                    List<PersonasEntity> perso = query.list();
                    for (PersonasEntity personasEntity : perso) {
                        persona = personasEntity;
                        System.out.println(persona.toString() + "\n ID del profesor " + pr.getIdprofesor() + " Departamento al que pertenece: " + dep.getNombreDept() + "\n");
                    }

//                        System.out.println(dep.getIddepartamentos() + " " + dep.getNombreDept() + " " + pr.getIdprofesor());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }
    //--------------------------------------------------------------------------------------------------------


    //Profesores

    //Departamento
    public void add_profesor() {
        session.beginTransaction();

        try {

            ProfesorEntity profesorEntity = new ProfesorEntity();

            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Inserta el id de personas");
            int id_personas = scanner1.nextInt();
            profesorEntity.setIdPersonas(id_personas);


            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Inserta el id de dept");
            int id_dept = scanner2.nextInt();
            profesorEntity.setIdDept(id_dept);

            session.saveOrUpdate(profesorEntity);


        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }


    public void eliminar_profesor() {
        session.beginTransaction();

        try {

            ProfesorEntity profesorEntity = new ProfesorEntity();

            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Inserta el id para eliminar");
            int id_profesor = scanner1.nextInt();

            profesorEntity.setIdprofesor(id_profesor);
            session.delete(profesorEntity);


        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }


    public void modificar_profesor() {
        session.beginTransaction();

        try {


            Scanner scanner3 = new Scanner(System.in);
            System.out.println("Inserta el id para modificar");
            int id_profesor = scanner3.nextInt();
            ProfesorEntity profesorEntity = session.get(ProfesorEntity.class, id_profesor);

            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Inserta el id de la persona");
            int id_personas = scanner1.nextInt();
            profesorEntity.setIdPersonas(id_personas);


            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Inserta el id de departamento para modificar");
            int id_dept = scanner2.nextInt();
            profesorEntity.setIdDept(id_dept);


            session.update(profesorEntity);


        } catch (Exception e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();

    }

    //--------------------------------------!!!!!!!!!!!!!!!!!MENUS!!!!!!!!!!!!!-------------------------------------------------------------------


    public void menu2() throws FileNotFoundException, XPathExpressionException {
        boolean bandera = true;
        while (bandera) {
            System.out.println("********************************************************");
            System.out.println("Elige opcion: ");
            System.out.println("1.-Departamento");
            System.out.println("2.-Personas");
            System.out.println("3.-Profesores");
            System.out.println("salir");
            System.out.println("*********************************************************");


            Scanner scanner = new Scanner(System.in);

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    menu3();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "2":
                    menu4();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "3":
                    menu5();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "salir":
                    bandera = false;
                    break;
            }
        }
    }


    public void menu7() throws FileNotFoundException, XPathExpressionException {
        boolean bandera = true;
        while (bandera) {
            System.out.println("********************************************************");
            System.out.println("Elige opcion: ");
            System.out.println("1.-Consultar por ID");
            System.out.println("2.-Consultar por nombre dept");
            System.out.println("salir");
            System.out.println("*********************************************************");


            Scanner scanner = new Scanner(System.in);

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    buscardep_porid();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "2":
                    buscardep_porNombre();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "salir":
                    bandera = false;
                    break;
            }
        }
    }


    public void menu6() throws FileNotFoundException, XPathExpressionException {
        boolean bandera = true;
        while (bandera) {
            System.out.println("********************************************************");
            System.out.println("Elige opcion: ");
            System.out.println("1.-Buscar por ID");
            System.out.println("2.-Buscar por NIF");
            System.out.println("3.-Buscar por Nombre");
            System.out.println("4.-Buscar por Primer Apellido");
            System.out.println("5.-Buscar por Segundo Apellido");
            System.out.println("salir");
            System.out.println("*********************************************************");


            Scanner scanner = new Scanner(System.in);

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    buscarpersonas_porId();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "2":
                    buscarpersonas_porNIF();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "3":
                    buscarpersonas_porNombre();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "4":
                    buscarpersonas_porApellido1();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "5":
                    buscarpersonas_porApellido2();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "salir":
                    bandera = false;
                    break;
            }
        }
    }


    public void menu3() throws FileNotFoundException, XPathExpressionException {
        boolean bandera = true;
        while (bandera) {
            System.out.println("********************************************************");
            System.out.println("Elige opcion: ");
            System.out.println("1.-Insertar dept");
            System.out.println("2.-Eliminar dept");
            System.out.println("3.-Modificar un dept");
            System.out.println("4.-Consultar un dept");
            System.out.println("salir");
            System.out.println("*********************************************************");


            Scanner scanner = new Scanner(System.in);

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    add_dept();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "2":
                    eliminar_dept();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "3":
                    modificar_dept();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "4":
                    menu7();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;

                case "salir":
                    bandera = false;
                    break;
            }
        }
    }


    public void menu4() throws FileNotFoundException, XPathExpressionException {
        boolean bandera = true;
        while (bandera) {
            System.out.println("********************************************************");
            System.out.println("Elige opcion: ");
            System.out.println("1.-Insertar persona");
            System.out.println("2.-Eliminar persona");
            System.out.println("3.-Modificar una persona");
            System.out.println("4.-Consultar una persona");
            System.out.println("5.-Consultar una persona en orden");
            System.out.println("salir");
            System.out.println("*********************************************************");


            Scanner scanner = new Scanner(System.in);

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    add_personas();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "2":
                    eliminar_personas();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "3":
                    modificar_personas();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "4":
                    menu6();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "5":
                    menu8();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "salir":
                    bandera = false;
                    break;
            }
        }
    }


    public void menu8() throws FileNotFoundException, XPathExpressionException {
        boolean bandera = true;
        while (bandera) {
            System.out.println("********************************************************");
            System.out.println("Elige opcion: ");
            System.out.println("1.-Ordenar por nombre");
            System.out.println("2.-Ordenar por apellido1");
            System.out.println("3.-Ordenar por apellido2");
            System.out.println("4.-Ordenar por fechaNacimiento");
            System.out.println("salir");
            System.out.println("*********************************************************");

            Scanner scanner = new Scanner(System.in);

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    try {
                        session.beginTransaction();
                        Query query = session.createQuery("from PersonasEntity ");
                        List<PersonasEntity> personasEntitys = query.list();
                        Collections.sort(personasEntitys, new Comparator<PersonasEntity>() {
                            @Override
                            public int compare(PersonasEntity o1, PersonasEntity o2) {
                                return o1.getNombre().compareTo(o2.getNombre());
                            }
                        });

                        for (PersonasEntity personasEntity : personasEntitys) {
                            System.out.println(personasEntity.toString());

                        }
                        session.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        session.beginTransaction();
                        Query query = session.createQuery("from PersonasEntity ");
                        List<PersonasEntity> personasEntitys = query.list();
                        Collections.sort(personasEntitys, new Comparator<PersonasEntity>() {
                            @Override
                            public int compare(PersonasEntity o1, PersonasEntity o2) {
                                return o1.getPrimerApellido().compareTo(o2.getPrimerApellido());
                            }
                        });

                        for (PersonasEntity personasEntity : personasEntitys) {
                            System.out.println(personasEntity.toString());
                        }

                        session.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                break;
                case "3":
                    try{
                        session.beginTransaction();
                        Query query = session.createQuery("from PersonasEntity ");
                        List<PersonasEntity> personasEntitys = query.list();
                        Collections.sort(personasEntitys, new Comparator<PersonasEntity>() {
                            @Override
                            public int compare(PersonasEntity ob1, PersonasEntity ob2) {
                                return ob1.getSegundoApellido().compareTo(ob2.getSegundoApellido());
                            }
                        });
                        for (PersonasEntity personasEnti : personasEntitys) {
                            System.out.println(personasEnti.toString());
                        }
                        session.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    try{
                        session.beginTransaction();
                        Query query = session.createQuery("from PersonasEntity ");
                        List<PersonasEntity> personasEntitys = query.list();
                        Collections.sort(personasEntitys, new Comparator<PersonasEntity>() {
                            @Override
                            public int compare(PersonasEntity ob1, PersonasEntity ob2) {
                                return ob1.getFechaNacimiento().compareTo(ob2.getFechaNacimiento());
                            }
                        });
                        for (PersonasEntity personasEnti : personasEntitys) {
                            System.out.println(personasEnti.toString());
                        }
                        session.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "salir":
                    bandera = false;
                    break;
            }
        }
    }


    public void menu5() throws FileNotFoundException, XPathExpressionException {
        boolean bandera = true;
        while (bandera) {
            System.out.println("********************************************************");
            System.out.println("Elige opcion: ");
            System.out.println("1.-Insertar profesor");
            System.out.println("2.-Eliminar profesor");
            System.out.println("3.-Modificar un profesor");
            System.out.println("salir");
            System.out.println("*********************************************************");


            Scanner scanner = new Scanner(System.in);

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    add_profesor();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "2":
                    eliminar_profesor();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "3":
                    modificar_profesor();
                    //Aqui hacemos las consultas en que le pasamos la expresion segun lo que quiere saber
                    break;
                case "salir":
                    bandera = false;
                    break;
            }
        }
    }
}
