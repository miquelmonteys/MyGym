import {RoleModel} from "./role.model";


export class UserModel {
  id: number;
  username: string;
  name: string;
  email: string;
  roles : RoleModel[];
}
