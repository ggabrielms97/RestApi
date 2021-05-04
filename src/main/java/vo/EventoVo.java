package vo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.apirest.model.Edicao;
import com.apirest.model.Evento;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","nome","sigla","area","inst","edicao"})
@Data
@Entity
@Table(name = "Evento")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EventoVo extends RepresentationModel<EventoVo> implements Serializable {

	private static final long serialVersionUID = -1883365097997502201L;

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("nome")
    private String nome; 
	
	@JsonProperty("sigla")
    private String sigla;
	
	@JsonProperty("area")
    private String areaCon; 
	
	@JsonProperty("inst")
    private String instOrg; 
	
	@JsonProperty("edicao")
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_edicoes")
    private List<Edicao> edicoes;	   
	
    public static EventoVo create(Evento evento) {
    	return new ModelMapper().map(evento, EventoVo.class);
    }

}