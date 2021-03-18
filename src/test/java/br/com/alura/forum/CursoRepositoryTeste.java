package br.com.alura.forum;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Usar quando houver outro Database que não é h2
@ActiveProfiles("test")
public class CursoRepositoryTeste {

    @Autowired
    private CursoRepository repository;

    @Test
    public void deveriaCarregarUmCursoAoBuscarPeloSeuNome(){
        String nomeCurso = "HTML 5";
        Curso curso = repository.findByNome(nomeCurso);

        Assert.assertNotNull(curso);
        Assert.assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    public void NaodeveriaCarregarUmCursoAoBuscarPeloSeuNome(){
        String nomeCurso = "JPA";
        try{
            Curso curso = repository.findByNome(nomeCurso);
            Assert.assertNull(curso);
        }catch (Exception e){
            fail("ERRO!!"+e);
        }



    }
}
