<?php

declare(strict_types=1);

namespace Core\Domain\Shared\Event;

trait Eventable
{
    private array $events = [];

    public function recordEvent(object $eventInterface): void
    {
        $this->events[] = $eventInterface;
    }

    /**
     * @return object[] events
     */
    public function releaseEvents(): array
    {
        $events = $this->events;
        $this->events = [];
        return $events;
    }
}
