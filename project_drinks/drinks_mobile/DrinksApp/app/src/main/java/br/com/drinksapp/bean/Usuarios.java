package br.com.drinksapp.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Silvio Cedrim on 27/10/2017.
 */

public class Usuarios implements Serializable {


    long codUsuario;

    String nome;

    String email;
    @SerializedName("senha")
    String senha;

    String telefone;

    String erro;

    int tipo_erro;

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public int getTipo_erro() {
        return tipo_erro;
    }

    public void setTipo_erro(int tipo_erro) {
        this.tipo_erro = tipo_erro;
    }

    public Usuarios() {
    }

    public Usuarios(long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Usuarios(String nome, String email, String senha, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public Usuarios(long codUsuario, String nome, String email, String senha, String telefone) {
        this.codUsuario = codUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
