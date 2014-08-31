package net.sf.gazpachoquest.domain.permission;

import javax.persistence.Entity;

import net.sf.gazpachoquest.domain.core.Research;
import net.sf.gazpachoquest.domain.permission.QuestionnairDefinitionPermission.Builder;
import net.sf.gazpachoquest.domain.user.Group;
import net.sf.gazpachoquest.domain.user.Role;
import net.sf.gazpachoquest.domain.user.User;
import net.sf.gazpachoquest.types.Perm;

@Entity
public class ResearchPermission extends AbstractPermission<Research> {

    private static final long serialVersionUID = 8330893603469347818L;

    public ResearchPermission() {
        super();
    }

    public ResearchPermission(User user, Role role, Integer mask, Research target) {
        super(user, role, mask, target);
    }

    public static Builder with() {
        return new Builder();
    }

    public static class Builder {
        private User user;
        private Role role;
        private int mask;
        private Research target;

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder mask(int mask) {
            this.mask = mask;
            return this;
        }

        public Builder target(Research target) {
            this.target = target;
            return this;
        }

        public Builder addPerm(Perm perm) {
            if ((mask & perm.getMask()) != perm.getMask()) {
                mask += perm.getMask();
            }
            return this;

        }

        public ResearchPermission build() {
            return new ResearchPermission(user, role, mask, target);
        }
    }

}
