package tech.devinhouse.m01s06servlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tech.devinhouse.m01s06servlet.model.Pessoa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/pessoa")
public class PessoaServlet extends HttpServlet {

    private static List<Pessoa> pessoas = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.getWriter().println(pessoas);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        Integer idade = Integer.parseInt(req.getParameter("idade"));

        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setIdade(idade);

        pessoas.add(pessoa);

        resp.getWriter().println(pessoa);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nome");
        Integer idade = Integer.parseInt(req.getParameter("idade"));

        Pessoa pessoa = findById(id);
        if (pessoa == null){
            resp.getWriter().println(false);
            return;
        }
        pessoa.setNome(nome);
        pessoa.setIdade(idade);

        resp.getWriter().println(pessoa);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Pessoa pessoa = findById(id);

        if (pessoa == null){
            resp.getWriter().println(false);
            return;
        }
        pessoas.remove(pessoa);
        resp.getWriter().println(true);

    }

    private Pessoa findById(Integer id) {
        for (Pessoa pessoa: pessoas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null;
    }


}
