<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Event;

interface EventDispatcherInterface
{
    public function dispatch(object $eventInterface): void;
}
