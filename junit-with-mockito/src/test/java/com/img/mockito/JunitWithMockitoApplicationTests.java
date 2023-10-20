package com.img.mockito;

import com.img.mockito.model.Dummy;
import com.img.mockito.repository.DummyRepository;
import com.img.mockito.service.DummyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class JunitWithMockitoApplicationTests {

	@InjectMocks
	private DummyService dummyService;
	@Mock
	private DummyRepository repository;
	private Dummy dummy;
	@BeforeEach
	void setUp(){
		dummy=new Dummy(1,"Praveen",25,"Male");
	}
	@Test
	public void save() {
		Mockito.when(repository.save(dummy)).thenReturn(dummy);
		Dummy dummy1=dummyService.add(dummy);
		assertAll(
				()->assertEquals("Praveen",dummy1.getName()),
				()->assertNotNull(dummy1)
		);
	}
	@Test
	public void fetch(){
		List<Dummy> dummies=List.of(new Dummy(1,"Praveen",25,"Male"),
				                    new Dummy(2,"Dinesh",22,"Male"),
				                    new Dummy(3,"Prasanna",25,"Male"));

		Mockito.when(repository.findAll()).thenReturn(dummies);
		List<Dummy> list=dummyService.getDummies();
		assertAll(
				()->assertEquals(3,repository.findAll().size())
		);
	}
	@Test
	public void update(){
		Mockito.when(repository.findById(1)).thenReturn(Optional.of(dummy));
		Mockito.when(repository.save(dummy)).thenReturn(dummy);
		dummy.setName("Praveen Kumar");
		Dummy update=dummyService.update(1,dummy);
		assertAll(
				()->assertNotNull(update),
				()->assertEquals("Praveen Kumar",update.getName()),
				()->assertEquals(25,update.getAge())
		);
	}
    @Test
	public void delete(){
		dummyService.delete(1);
		verify(repository,times(1)).deleteById(1);
	}
}
