# TP Sécurité des Micro Services avec Spring Security et JWT

![shema](https://user-images.githubusercontent.com/62752474/141691080-973fc7fe-746a-4cc7-891b-224886256062.PNG)

## Couche DAO
### Classe AppRole
![appRole](https://user-images.githubusercontent.com/62752474/141691127-2414637a-ae4b-4a70-b545-acbac8810051.PNG)

### Classe AppUser
![AppUser](https://user-images.githubusercontent.com/62752474/141691129-ec6f6fe6-e77b-473d-89f4-e4bfdeec9d18.PNG)

## Couche Service

###  l’interface AccountService:
![Account](https://user-images.githubusercontent.com/62752474/141691397-70ae091f-56ba-4e8f-9f67-c24f6304cd7e.PNG)

### implémentation de cette interface (AccountServiceImpl)
![implAccount](https://user-images.githubusercontent.com/62752474/141691399-dcf2e31f-eaa8-44cb-9049-da893dd4e9bf.PNG)

![impl2Account](https://user-images.githubusercontent.com/62752474/141691398-4a930100-24ff-492b-98fb-0352b34a0a68.PNG)

### Tester les opérations de la couche service
![auth](https://user-images.githubusercontent.com/62752474/141691491-ddd72f4b-11ea-4f34-bae4-311dcdb5461b.PNG)

![usersApi](https://user-images.githubusercontent.com/62752474/141691569-71dfefc9-ece0-4391-9451-4c6d6e2a0331.PNG)

### Créer un RestController qui permet de gérer les utilisateurs et les rôles
![restController](https://user-images.githubusercontent.com/62752474/141691616-1bf1cc39-c771-4f8e-bbf0-7dc948571575.PNG)

### Sécuriser le service en instaurant un système d’authentification Stateless avec Spring Security et Json Web Token en délivrant au client le Access Token et le refresh token (JWT Authentication Filter et JWT Authorization Filter)
> JWT Authentication Filter 
![jwtAuthtetification](https://user-images.githubusercontent.com/62752474/141691656-829a84c2-8db6-4759-9823-bee638346f30.PNG)
> JWT Authorization Filter
![jwutAuthrizartion](https://user-images.githubusercontent.com/62752474/141691658-4812d1d5-e82c-44b2-a081-b234274e1f46.PNG)

### Tester les opérations du services d’authentification avec un client REST : Advenced Rest Client
![arcauthadmin](https://user-images.githubusercontent.com/62752474/141691489-e605035c-759d-4169-a5e8-9c2e68d11cd2.PNG)
![jwt](https://user-images.githubusercontent.com/62752474/141691492-7562adcd-b8ae-46a9-896a-dd0774af2589.PNG)
![refreshToken](https://user-images.githubusercontent.com/62752474/141691497-21dcd995-dc4a-4666-9b81-8dea748c4bc6.PNG)

![jwtauthusers](https://user-images.githubusercontent.com/62752474/141691494-a900844f-3614-4e72-9976-25d44a486d0b.PNG)

