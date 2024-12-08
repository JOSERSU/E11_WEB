package com.upiiz.SecurityInDataBase;

import com.upiiz.SecurityInDataBase.entities.PermissionEntity;
import com.upiiz.SecurityInDataBase.entities.RolEntity;
import com.upiiz.SecurityInDataBase.entities.RolEnum;
import com.upiiz.SecurityInDataBase.entities.UserEntity;
import com.upiiz.SecurityInDataBase.repository.PermissionRepository;
import com.upiiz.SecurityInDataBase.repository.RolRepository;
import com.upiiz.SecurityInDataBase.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SecurityInDataBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityInDataBaseApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository, RolRepository rolRepository) {
		return args -> {
			PermissionEntity createPermission = PermissionEntity
					.builder()
					.name("CREATE")
					.build();
			PermissionEntity deletePermission = PermissionEntity
					.builder()
					.name("DELETE")
					.build();
			PermissionEntity updatePermission = PermissionEntity
					.builder()
					.name("UPDATE")
					.build();
			PermissionEntity readPermision = PermissionEntity
					.builder()
					.name("READ")
					.build();
			PermissionEntity deployPermision = PermissionEntity
					.builder()
					.name("DEPLOY")
					.build();

			//permissionRepository.saveAll(List.of(createPermission, deletePermission, updatePermission, readPermision, deployPermision));
			//roles
			RolEntity adminRole = RolEntity
					.builder()
					.rolEnum(RolEnum.ADMIN)
					.permisions(Set.of(createPermission, deletePermission, updatePermission, readPermision))
					.build();
			RolEntity userRole = RolEntity
					.builder()
					.rolEnum(RolEnum.USER)
					.permisions(Set.of(updatePermission, readPermision))
					.build();
			RolEntity guestRole = RolEntity
					.builder()
					.rolEnum(RolEnum.GUEST)
					.permisions(Set.of(readPermision))
					.build();
			RolEntity developerRole = RolEntity
					.builder()
					.rolEnum(RolEnum.DEVELOPER)
					.permisions(Set.of(deployPermision, createPermission, deletePermission, updatePermission, readPermision))
					.build();
			//usuarios
			UserEntity juan = UserEntity
					.builder()
					.username("juan")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(developerRole))
					.build();
			UserEntity jose = UserEntity
					.builder()
					.username("jose")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.accountNoLocked(true)
					.roles(Set.of(userRole))
					.build();
			UserEntity admin = UserEntity
					.builder()
					.username("admin")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(adminRole))
					.build();
			UserEntity guest = UserEntity
					.builder()
					.username("guest")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(guestRole))
					.build();
			UserEntity ana = UserEntity
					.builder()
					.username("ana")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(developerRole))
					.build();


			//rolRepository.saveAll(List.of(adminRole, userRole, guestRole, developerRole));
			userRepository.saveAll(List.of(juan, jose, admin, guest, ana));
		};
	}
}
