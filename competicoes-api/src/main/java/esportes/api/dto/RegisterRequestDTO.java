package esportes.api.dto;

public record RegisterRequestDTO(Long empresaId, String name, String email, String password) {
}
