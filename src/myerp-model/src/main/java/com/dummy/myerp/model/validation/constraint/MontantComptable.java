package com.dummy.myerp.model.validation.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Digits;
import java.lang.annotation.*;


/**
 * Contrainte à apposer sur les attibuts de type "montant comptable"
 * <p>
 * ette contrainte est composée de :
 * <ul>
 *     <li>@{@link Digits}</li>
 * </ul>
 * <p>
 * Types supportés :
 * <ul>
 *     <li>{@link java.math.BigDecimal}</li>
 * </ul>
 */
@Digits(integer = 13, fraction = 2)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface MontantComptable {

    /**
     * @return Message de la violation
     */
    String message() default "Taux de TVA invalide";

    /**
     * @return Groupe de validation
     */
    Class<?>[] groups() default {};

    /**
     * @return Payload
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Interface permettant la déclaration de plusieurs {@link MontantComptable}
     */
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        /**
         * @return List des {@link MontantComptable}
         */
        MontantComptable[] value();
    }
}
