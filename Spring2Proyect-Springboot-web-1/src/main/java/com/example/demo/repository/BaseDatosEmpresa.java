package com.example.demo.repository;
import java.util.ArrayList;
import java.util.Iterator;
import com.example.demo.bean.Empresa; // Importa la clase Empresa
import org.springframework.stereotype.Service;




@Service
public class BaseDatosEmpresa {

    ArrayList<Empresa> empresas = new ArrayList<Empresa>(); // Cambia el tipo de datos a Empresa

    public BaseDatosEmpresa() {
        empresas.add(new Empresa(1, "WebArt", "empresa diseñadora de painas web")); // Añade empresas en el constructor
        empresas.add(new Empresa(2, "Isard", "empresa tecnologica de escritorios en la nube"));
        // ... Añade más empresas si es necesario
    }

    public void insertar(Empresa empresa) {
        empresas.add(empresa);
    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }

    public void borrar(Long id) {
        Iterator<Empresa> it = empresas.iterator();
        while (it.hasNext()) {
            Empresa empresa = it.next();
            if (empresa.getId()==(id)) {
                it.remove();
                break;
            }
        }
    }

    public void modificar(Empresa empresa) {
        for (Empresa emp : empresas) {
            if (emp.getId()==(empresa.getId())) {
                emp.setNom(empresa.getNom());
                emp.setDescripcio(empresa.getDescripcio());
                break;
            }
        }
    }

    public Empresa getEmpresa(Long id) {
        for (Empresa empresa : empresas) {
            if (empresa.getId()==(id)) {
                return empresa;
            }
        }
        return null;
    }
}
