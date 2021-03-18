package br.com.alura.forum;


import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TopicoRepositoryTeste {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Test
    public void testaSeExsiteTopicosPorCurso(){
        String curso = "HTML 5";
        Pageable firstPageWithOneElement = PageRequest.of(0,1);
        Page<Topico> topico = topicoRepository.findByCursoNome(curso, firstPageWithOneElement);

        Assert.assertNotNull(topico);
        Assert.assertEquals(1, topico.getSize());
    }

    @Test
    public void testaSeNaoExsiteTopicosPorCurso(){
        String curso = "JPA";
        Pageable firstPageWithOneElement = PageRequest.of(0,1);
        Page<Topico> topico = topicoRepository.findByCursoNome(curso, firstPageWithOneElement);

        System.out.println("_--------------"+topico.toString());
        //Assert.assertNull(topico);
        Assert.assertEquals(0, topico.getTotalPages());
        //Assert.assertEquals(0, topico.getTotalElements());
    }

    @Test
    public void testaSeInsereNoBanco(){

        Curso curso = cursoRepository.findByNome("HTML 5");
        Topico topico = new Topico("Dúvidas de Teste","Mensagem de Teste",curso);
        Topico topNoBanco;
        topicoRepository.save(topico);
        topNoBanco = topicoRepository.findByTitulo(topico.getTitulo());

        Assert.assertNotNull(topNoBanco);
    }

}
