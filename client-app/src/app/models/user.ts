import {Gender} from "./gender.enum";

export class User {
  id: string;
  email: string;
  authority;
  active: boolean;
  token: string;
  refreshToken: string;
  firstName: string;
  lastName: string;
  gender: Gender;
  phone: string;
  skype?: string;
  facebook?: string;
  linkedIn?: string;
  telegram?: boolean;
  whatsUp?: boolean;
}
