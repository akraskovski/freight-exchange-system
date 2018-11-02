import {Authority} from "../authority.enum";

export class SignUpUser {
  authProfileId: string;
  email: string;
  password: string;
  authority: Authority;
  firstName: string;
  lastName: string;
  gender: string;
  phone: string;
  skype?: string;
  facebook?: string;
  linkedIn?: string;
  telegram?: string;
  whatsUp?: string;
}
