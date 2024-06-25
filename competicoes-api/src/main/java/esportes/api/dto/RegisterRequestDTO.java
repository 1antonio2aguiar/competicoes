package esportes.api.dto;


import esportes.api.domain.user.User;

public record RegisterRequestDTO(Long id, Long empresaId, String name, String email, String password) {
    public  RegisterRequestDTO(User user){
        this(user.getId(),
            user.getEmpresaId(),
            user.getName(),
            user.getEmail(),
            user.getPassword());
    }
}
